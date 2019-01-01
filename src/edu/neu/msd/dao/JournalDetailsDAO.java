package edu.neu.msd.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.neu.msd.constants.AllConstantParams;
import edu.neu.msd.datasource.DataSource;
import edu.neu.msd.dto.Author;
import edu.neu.msd.dto.CommonAuthorMapper;
import edu.neu.msd.dto.Conference;
import edu.neu.msd.dto.Journal;
import edu.neu.msd.dto.JournalMapper;
import edu.neu.msd.dto.PublicationMapper;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class JournalDetailsDAO extends DataSource implements AllConstantParams{
	
	// get journal details by author Name name,
	/***
	 * 
	 * @param auth
	 * @param minPage
	 * @param maxPage
	 * @return
	 */
	public List<JournalMapper> getJournalDetails(Author auth, int minPage, int maxPage)
	{

		Connection con;
		try {
		con = this.getCon();
		Map<String,Class<?>> myMap=con.getTypeMap();
		myMap.put(JOURNAL_OBJ_TYPE, Class.forName(JOURNAL_MAPPER));
		con.setTypeMap(myMap);
		
		List<JournalMapper> journalList=new ArrayList<JournalMapper>();
		
		OracleCallableStatement ocs = (OracleCallableStatement)con.prepareCall(GET_JOURNAL_DETAILS); 
		ocs.setString(1, auth.getAuthName());
		ocs.setInt(2, minPage);
		ocs.setInt(3,maxPage);
		ocs.setString(4,auth.getYear());
		ocs.registerOutParameter(5, OracleTypes.ARRAY, JOURNAL_OBJ_TAB); 
		ocs.execute();
		
		Object[] data = (Object[]) ((Array) ocs.getObject(5)).getArray();
		System.out.println(data.length);
		System.out.println(auth.getAuthName());
		for(Object tmp : data) {
			journalList.add((JournalMapper)tmp);
			
        }
		return journalList;
		
		
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
		public List<CommonAuthorMapper> getAuthorsbyJournal(Author auth,int minPage,int maxPage){
			Connection con;
			try {
				con = this.getCon();
			Map<String,Class<?>> myMap=con.getTypeMap();
			myMap.put(COMMON_AUTHORS_OBJ, Class.forName(COMMON_AUTHOR_MAPPER));
			con.setTypeMap(myMap);
			List<CommonAuthorMapper> authList=new ArrayList<CommonAuthorMapper>();
			OracleCallableStatement ocs = (OracleCallableStatement)con.prepareCall( GET_JOURNAL_AUTHORS);
			ocs.setString(1, auth.getJournalName());
			ocs.setString(2, auth.getTitle());
			ocs.setString(3,auth.getYear());
			ocs.setInt(4, minPage);
			ocs.setInt(5,maxPage);
			ocs.registerOutParameter(6, OracleTypes.ARRAY, COMMON_AUTHORS_OBJ_TAB); 
			ocs.execute();
			
			Object[] data = (Object[]) ((Array) ocs.getObject(6)).getArray();
			
			for(Object tmp : data) {
				//System.out.println((String)tmp);
				authList.add((CommonAuthorMapper)tmp);	
	        }
			
			/*System.out.println("*****************************");
			for(CommonAuthorMapper authObj: authList)
			{
				System.out.println(authObj.getAuthName());
			}
			System.out.println("*****************************");*/
			
			return authList;
			
			}
			catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
	}
}



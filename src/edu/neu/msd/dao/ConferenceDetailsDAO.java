package edu.neu.msd.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.neu.msd.constants.AllConstantParams;
import edu.neu.msd.datasource.DataSource;
import edu.neu.msd.dto.Author;
import edu.neu.msd.dto.CommonAuthorMapper;
import edu.neu.msd.dto.Conference;
import edu.neu.msd.dto.PublicationMapper;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class ConferenceDetailsDAO extends DataSource implements AllConstantParams{
	
	/***
	 * IMP FUNCTION
	 * @param auth
	 * @param minPage
	 * @param maxPage
	 * @return
	 */
	public List<PublicationMapper> getConferenceDetails(Author auth,int minPage,int maxPage)   //return conference
	{
		Connection con;
		try {
			con = this.getCon();
		Map<String,Class<?>> myMap=con.getTypeMap();

		
		myMap.put(PUB_OBJ_TYPE_SQL, Class.forName(PUBLICATION_MAPPER));

		con.setTypeMap(myMap);
		List<PublicationMapper> pubList=new ArrayList<PublicationMapper>();
		OracleCallableStatement ocs = (OracleCallableStatement)con.prepareCall(GET_CONFERENCE_DEATILS); 
		ocs.setString(1, auth.getAuthName());
		ocs.setInt(2, minPage);
		ocs.setInt(3,maxPage);
		ocs.setString(4,auth.getYear());
		ocs.registerOutParameter(5, OracleTypes.ARRAY, SCHEMA+PUB_OBJ_TAB_TYPE_SQL); 


		ocs.execute();
		
		Object[] data = (Object[]) ((Array) ocs.getObject(5)).getArray();
		for(Object tmp : data) {
			pubList.add((PublicationMapper)tmp);

        }

		
		if(auth.getTitle() != null){
			List<PublicationMapper> refinedPubList = new ArrayList<>();
			for(PublicationMapper pm : pubList){
				//System.out.println(pm.getTitle());
				if(pm.getTitle().toLowerCase().contains(auth.getTitle().toLowerCase())){
					refinedPubList.add(pm);
				}
			}
			return refinedPubList;
		}

		return pubList;
		
		
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	

	
	

	/*
	 * Get conference details based on the author selected by user.
	 * Returns the list of the conferences for the given author
	 */
	/***
	 * 
	 * @param auth
	 * @param minPage
	 * @param maxPage
	 * @return
	 */
	public List<CommonAuthorMapper> getAuthorsbyConference(Author auth,int minPage,int maxPage){
		Connection con;
		try {
			
			String[] inConference=auth.getConfereceName().split(" ");
			StringBuilder strBuild=new StringBuilder();
			
			
			
			for(String str : inConference)
			{
				strBuild.append("'"+str+"',");
			}
			
			String strFinal=strBuild.toString().substring(0,strBuild.toString().length()-1);
			System.out.println(strFinal.toUpperCase());
			
			
			
			con = this.getCon();
		Map<String,Class<?>> myMap=con.getTypeMap();
		myMap.put("AUTHOR_PUBS_DETAILS_OBJ", Class.forName("edu.neu.msd.dto.CommonAuthorMapper"));
		con.setTypeMap(myMap);
		List<CommonAuthorMapper> authList=new ArrayList<CommonAuthorMapper>();
		OracleCallableStatement ocs = (OracleCallableStatement)con.prepareCall("{ call GET_CONFERENCE_AUTHORS(?,?,?,?,?,?)}" );
		ocs.setString(1, auth.getConfereceName());
		ocs.setString(2, auth.getTitle());
		ocs.setString(3,auth.getYear());
		ocs.setInt(4, minPage);
		ocs.setInt(5,maxPage);
		ocs.registerOutParameter(6, OracleTypes.ARRAY, "AUTHOR_PUBS_DETAILS_TAB"); 
		ocs.execute();
		
		Object[] data = (Object[]) ((Array) ocs.getObject(6)).getArray();
		for(Object tmp : data) {
			//System.out.println((String)tmp);
			authList.add((CommonAuthorMapper)tmp);
			
        }
		
		System.out.println("*****************************");
		for(CommonAuthorMapper authObj: authList)
		{
			System.out.println(authObj.getAuthName());
		}
		System.out.println("*****************************");
		
		return authList;
		
		
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}

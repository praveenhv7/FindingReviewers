package edu.neu.msd.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.neu.msd.datasource.DataSource;
import edu.neu.msd.dto.Author;
import edu.neu.msd.dto.CommonAuthorMapper;
import edu.neu.msd.dto.SearchAuthMapper;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class AllSearchDetailsDAO extends DataSource{


	
	public List<SearchAuthMapper> callProc(String confereceName,String journalName,Author auth,String choice,int pgMin,int pgMax) throws Exception
	{
		String journalNameIn=null;
		String conferenceNameIn=null;
		if(journalName!=null && journalName.trim().length()>1){
		Connection con=getCon();
		String sql="select JOURNAL,rownum from (Select distinct JOURNAL from articles_subset where UPPER(JOURNAL) like ? ) data where rownum<=30" ;
		System.out.println(sql);
		StringBuilder build=new StringBuilder();
		try {
			for(String jor:journalName.split(",")){
				System.out.println("'%"+jor+"%'");
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, "%"+jor+"%");
			ResultSet res=stmt.executeQuery();
			while(res.next()){
			
				System.out.println(res.getString(1));
				build.append(res.getString("JOURNAL"));
				build.append(",");
				
			}
			
		} 
			
			 journalNameIn = createInStatement(build.toString());
			 
		}catch(Exception e){
		journalNameIn=null;
		}
		
		}
		if(confereceName!=null ){
			if(confereceName.trim().length()>1)
		conferenceNameIn=createInStatement(confereceName);
			else
				conferenceNameIn=null;
		}
		else
			conferenceNameIn=null;
			
			System.out.println("JournalName "+journalNameIn);
			System.out.println("Conference Name" + conferenceNameIn);
			
			try {
				return callAllSearch(conferenceNameIn, journalNameIn,auth,choice,pgMin,pgMax);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
			
		
		
		
	}
	/***
	 * 
	 * @param conferenceName
	 * @param journalName
	 * @param auth
	 * @param choice
	 * @param minPg
	 * @param maxPg
	 * @throws Exception 
	 */
	public List<SearchAuthMapper> callAllSearch(String conferenceName,String journalName,Author auth,String choice,int minPg,int maxPg) throws Exception {
		
		Connection con;
		try {
			
			if(auth.getServedConference()!=null)
				auth.setServedConference(createInStatement(auth.getServedConference()));
			
			con = this.getCon();
		Map myMap=con.getTypeMap();
		myMap.put("SEARCH_RESULT_OBJECT", Class.forName("edu.neu.msd.dto.SearchAuthMapper"));
		con.setTypeMap(myMap);    
		
		
	
		
		OracleCallableStatement ocs = 
				  (OracleCallableStatement)con.prepareCall("{ call GET_CONFERENCE_DETAILS_DYNAMIC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
		ocs.setString(1, conferenceName);
		ocs.setString(2, journalName);
		ocs.setString(3, auth.getAuthName());
		ocs.setString(4, auth.getYear());
		ocs.setString(5, auth.getYearOption());
		ocs.setString(6, auth.getTitle());
		ocs.setString(7, choice);
		ocs.setInt(8, auth.getNoOfPublications());
		ocs.setInt(9, auth.getNoOfJournals());
		ocs.setInt(10,minPg);
		ocs.setInt(11,maxPg);
		ocs.setString(12, auth.getServedConference());
		ocs.setString(13, auth.getServedTitleOption());
		ocs.setString(14, auth.getServedChoice());
		ocs.registerOutParameter(15, OracleTypes.VARCHAR);
		ocs.registerOutParameter(16, OracleTypes.ARRAY,"SEARCH_RESULT_OBJECT_TAB");
		ocs.execute();
		System.out.println("Executed");
		String result=ocs.getString(15);
		System.out.println(result);
		List<SearchAuthMapper> authMapper=new ArrayList<>();
		Object[] data = (Object[]) ((Array) ocs.getObject(16)).getArray();
		for(Object tmp : data) {
			authMapper.add((SearchAuthMapper)tmp);
			
        }
		if(result!=null){
		if(result.contains("ERROR"))
		{
			System.out.println(result);
			throw new Exception("Error in Procedure GET_CONFERENCE_DETAILS_DYNAMIC");
		}
		}
		
		System.out.println("size of list "+authMapper.size());
		for(SearchAuthMapper auths:authMapper){
			System.out.println("Author Name "+auths.getAuthorName() + " count Publications "+auths.getCountPublications() +" count Journals "+auths.getCountJournals());
		}
		return authMapper;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		
		
	}
	
	public String createInStatement(String inStmt){
		
		StringBuilder build=new StringBuilder();
		boolean toggle=false;
		for(String str : inStmt.split(","))
		{
			if(!toggle)
			build.append(str+"'");
			else
				build.append("'"+str+"'");
			build.append(",");
			toggle=true;
		}
		
		int len=build.toString().length();
		String returnStr=build.toString().substring(0,len-2);
		return returnStr;
	}
	


}

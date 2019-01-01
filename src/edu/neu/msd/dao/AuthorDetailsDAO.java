package edu.neu.msd.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.neu.msd.constants.AllConstantParams;
import edu.neu.msd.datasource.DataSource;
import edu.neu.msd.dto.Author;
import edu.neu.msd.dto.AuthorsMapper;
import edu.neu.msd.dto.CommonAuthorMapper;
import edu.neu.msd.dto.PublicationMapper;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

//not sure on implementation of data or data representation.
public class AuthorDetailsDAO extends DataSource implements AllConstantParams{
	
	/*public List<AuthorsMapper> getAuthorDetailsBasedOnParams(Object author,int minPage,int maxPage){
		
		Connection con;
		try {
			con = this.getCon();
		Map myMap=con.getTypeMap();
		myMap.put("AUTHOR_OBJ_TYPE", Class.forName("edu.neu.msd.dto.AuthorsMapper"));
		con.setTypeMap(myMap);
		//ArrayDescriptor descriptor=ArrayDescriptor.createDescriptor("PRAVEEN.PUBLICATIONS_OBJ_TYPE",con);
//      
		List<AuthorsMapper> pubList=new ArrayList<AuthorsMapper>();
		
		OracleCallableStatement ocs = 
				  (OracleCallableStatement)con.prepareCall("{ call GET_AUTHORS_WITH_N_PUBS(?,?,?,?,?,?,?,?) }"); 
		ocs.setString(1, "CONF");
		ocs.setInt(2, 5);
		ocs.setString(3, "CONF");
		ocs.setString(4, null);
		ocs.setString(5, "YEAR");
		ocs.setInt(6, minPage);
		ocs.setInt(7,maxPage);
		ocs.registerOutParameter(8, OracleTypes.ARRAY, "AUTHOR_OBJ_TAB"); 
		
		ocs.execute();
		
		Object[] data = (Object[]) ((Array) ocs.getObject(8)).getArray();
		for(Object tmp : data) {
			pubList.add((AuthorsMapper)tmp);
			
        }
		return pubList;
		
		
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	
public List<Author> getCoAuthorDetails(Author author){
	
	List<Author> coAuthors=null;
	try{
		Connection con=getCon();
		coAuthors=new ArrayList<Author>();
		String sql=CO_AUTHORS_SQL;
		PreparedStatement prepPub=con.prepareStatement(sql);
		prepPub.setString(1, author.getAuthName().toUpperCase());
		prepPub.setString(2, author.getAuthName().toUpperCase());
		prepPub.setString(3, author.getAuthName().toUpperCase());
		prepPub.setString(4, author.getAuthName().toUpperCase());
		ResultSet rsPub=prepPub.executeQuery();
		
		while(rsPub.next())
		{
			Author coAuthor=new Author();
			coAuthor.setAuthName(rsPub.getString(1));
			coAuthors.add(coAuthor);
		}
		
		
		return coAuthors;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return coAuthors;
	}

	public Object getAuthorDetails(Object author)
	{
		return author;
		
	}
	

	public List<CommonAuthorMapper> getAuthorsByName(Author author,int minPage,int maxPage)

	{
		Connection con;
		try {
			con = this.getCon();
		Map myMap=con.getTypeMap();
		myMap.put("AUTHOR_PUBS_DETAILS_OBJ", Class.forName("edu.neu.msd.dto.CommonAuthorMapper"));
		con.setTypeMap(myMap);    
		List<CommonAuthorMapper> pubList=new ArrayList<CommonAuthorMapper>();

		OracleCallableStatement ocs = 
				  (OracleCallableStatement)con.prepareCall("{ call GET_AUTHORS_WITH_N_PUBS(?,?,?,?,?,?,?,?,?,?) }"); 
		ocs.setString(1, author.getConfereceName());
		ocs.setString(2, author.getAuthorName());
		ocs.setInt(3,author.getCommitteServed() );
		ocs.setInt(4, author.getNoOfPublications());
		ocs.setString(5,author.getTypeOfPublication() );
		ocs.setString(6, author.getJournalName());
		ocs.setString(7, null);
		ocs.setInt(8,minPage);
		ocs.setInt(9,maxPage);
		System.out.println(author.getConfereceName());
		System.out.println(author.getAuthorName());
		System.out.println(author.getCommitteServed());
		System.out.println(author.getNoOfPublications());
		System.out.println(author.getTypeOfPublication());
		System.out.println(author.getJournalName());

		ocs.registerOutParameter(10, OracleTypes.ARRAY, "AUTHOR_PUBS_DETAILS_TAB"); 
		
		ocs.execute();
		
		Object[] data = (Object[]) ((Array) ocs.getObject(10)).getArray();
		for(Object tmp : data) {
			pubList.add((CommonAuthorMapper)tmp);
			
        }
		System.out.println("*****************************");
		for(CommonAuthorMapper auth1: pubList)
		{
			//System.out.println(auth1.getAuthName());
		}
		System.out.println("*****************************");
		
	
		return pubList;
		
		
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**Gets list of shortlisted authors
	 * @return list of author
	 */
	public List<Author> getShortListedAuthors(){
		List<Author> shortlistedAuthors=null;
		try{
			Connection con=getCon();
			shortlistedAuthors=new ArrayList<Author>();
			String sql= SELECT_SHORLISTED_AUTHOR;
			PreparedStatement prepSA=con.prepareStatement(sql);
			ResultSet rs=prepSA.executeQuery();
			while(rs.next())
			{
				Author author=new Author();
				author.setAuthName(rs.getString(1).toLowerCase());
				author.setYear(null);
				author.setTitle(null);
				//System.out.println("shortlisted " + A);
				shortlistedAuthors.add(author);
			}
		return shortlistedAuthors;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return shortlistedAuthors;
		
	}
	
	/** Delete an author selected by the user. 
	 * @param author
	 * @param deleteAll
	 */
	public void deleteShortListedAuthors(String author, boolean deleteAll){
		try{
			Connection con=getCon();
			if (!deleteAll)
			{
				String sql= DELETE_SHORLISTED_AUTHOR;
				PreparedStatement prepSA=con.prepareStatement(sql);
				prepSA.setString(1, author.toUpperCase());
				prepSA.executeUpdate();
			}
			else{
				String sql= DELETE_ALL_SHORLISTED_AUTHOR;
				PreparedStatement prepSA=con.prepareStatement(sql);
				prepSA.executeUpdate();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/** Adds an author to the short list 
	 * @param author
	 */
	public void AddShortListedAuthor(String author){
		try{

			SimpleDateFormat format = new SimpleDateFormat( "yyyy/dd/MM" );  // United States style of format.
			Date myDate = new Date();
			Connection con=getCon();
			String sql= INSERT_SHORLISTED_AUTHOR;
			PreparedStatement prepSA=con.prepareStatement(sql);
			prepSA.setString(1, author.toUpperCase());
			prepSA.setString(2, null);
			prepSA.setString(3, null);
			prepSA.executeUpdate();
			System.out.println("successful");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	/** Gives the conference count and journal count for a given author
	 * @param authName
	 * @return
	 */
	public String getNumberOfConferencesandJournals(String authName)
	{
		String confCount = "";
		String jourCount = "";
		try{
			Connection con=getCon();
			String sql= GET_COUNT_CONEFRENCES;
			PreparedStatement prepConf=con.prepareStatement(sql);
			prepConf.setString(1, authName.toUpperCase());
			ResultSet rsConf=prepConf.executeQuery();
			 while(rsConf.next()) {
				    System.out.println("The count is " + rsConf.getInt(1));
				    confCount = Integer.toString(rsConf.getInt(1));
			}
			// get Journal Count
			sql= GET_COUNT_JOURNALS;
			PreparedStatement prepJour=con.prepareStatement(sql);
			prepJour.setString(1, authName.toUpperCase());
			ResultSet rsJour=prepJour.executeQuery();
			while(rsJour.next()) {
					System.out.println("The count is " + rsJour.getInt(1));
					jourCount = Integer.toString(rsJour.getInt(1));
				}

		return "Conference Publications: "+confCount +"  Journal Publications: "+ jourCount; 

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	public String getAuthorHomepage(String authname)
	{
		try{
			Connection con=getCon();
			String sql= GET_AUTHOR_HOMEPAGE;
			PreparedStatement prepConf=con.prepareStatement(sql);
			prepConf.setString(1, authname.toUpperCase());
			ResultSet rsWeb=prepConf.executeQuery();
			 while(rsWeb.next()) {
				    return rsWeb.getString(2);
				    
			}
		}
			catch(Exception e)
			{
					e.printStackTrace();
			}
				return "Not Available";
	}
	
	
	public String getAuthorAffiliation(String name) {
		// TODO Auto-generated method stub
		//System.out.println(name);
		try{
			Connection con=getCon();
			String sql= GET_AUTHOR_AFFILIATION;
			PreparedStatement prepConf=con.prepareStatement(sql);
			prepConf.setString(1, name.toUpperCase());
			ResultSet rsAffliation=prepConf.executeQuery();
			 while(rsAffliation.next()) {
				 
				    return rsAffliation.getString(2);
				    
			}
		}
			catch(Exception e)
			{
					e.printStackTrace();
			}
		
		return "Not Available";
	}
	
}

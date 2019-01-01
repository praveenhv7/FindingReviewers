package edu.msd.eventmanager;

import edu.neu.msd.dao.*;
import edu.neu.msd.dao.ConferenceDetailsDAO;

import edu.neu.msd.dto.*;
import edu.ui.JavaFX_uiTabPane;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
	
	
	/***
	 * Gets Co authors of an author
	 * Input Author Name
	 * Output List of Authors 
	 */
	public List<Author> getCoAuthors(Author auth)
	{
		
		
		try{
		
		AuthorDetailsDAO dao=new AuthorDetailsDAO();
		List<Author> coAuthors= dao.getCoAuthorDetails(auth);
		return coAuthors;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
		
	}
	
	/***
	 * 
	 * @param auth =>author object
	 * search based on conference name , Title and year
	 * @param minPage for pagination 
	 * @param maxPage for pagination
	 * @return List of Authors
	 */
	public List<CommonAuthorMapper> getConferenceAuthors(Author auth,int minPage,int maxPage)
	{
		
		
		List<CommonAuthorMapper> authConf=null;
		try{
		ConferenceDetailsDAO confDao=new ConferenceDetailsDAO();
		authConf=confDao.getAuthorsbyConference(auth,minPage,maxPage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			
		}
		return authConf;
		
	}
	
	/***
	 * 
	 * @param auth => Author object
	 * input => journal name, year and other attributes 
	 * @param minPage  
	 * @param maxPage
	 * @return List of author object 
	 */
	public List<CommonAuthorMapper> getJournalAuthors(Author auth,int minPage,int maxPage)
	{
		
		List<CommonAuthorMapper> authConf=null;
		try{
		JournalDetailsDAO journalDao=new JournalDetailsDAO();
		authConf=journalDao.getAuthorsbyJournal(auth,minPage,maxPage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return authConf;
	}
	/***
	 * 
	 * @param auth
	 * Number of publications of an author, conference or journal name.
	 * @param minPage
	 * @param maxPage
	 * @return Publication and Journal Details
	 */
	public List<CommonAuthorMapper> getAuthorWithNPubs(Author auth,int minPage,int maxPage) {
		List<CommonAuthorMapper> authConf = null;
		
		try {
			AuthorDetailsDAO authorDao = new AuthorDetailsDAO();
			authConf =authorDao.getAuthorsByName(auth, minPage, maxPage);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return authConf;
	}
	/***
	 * 
	 * @param auth
	 * author name, journal, year
	 * @param minPage
	 * @param maxPage
	 * @return List of author objects
	 */
	public List<JournalMapper> getAuthorDetailsJournals(Author auth,int minPage,int maxPage)
	{
		List<JournalMapper> authDetails=null;
		
		try{
			JournalDetailsDAO journalDet=new JournalDetailsDAO();
			authDetails=journalDet.getJournalDetails(auth, minPage, maxPage);
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return authDetails;
	}
	
	/***
	 * 
	 * @param auth
	 * @param minPage
	 * @param maxPage
	 * @return
	 */
	public List<PublicationMapper> getAuthorDetailsConferences(Author auth,int minPage,int maxPage)
	{
		List<PublicationMapper> authDetails=null;
		
		try{
			ConferenceDetailsDAO conferenceDet=new ConferenceDetailsDAO();
			authDetails=conferenceDet.getConferenceDetails(auth, minPage, maxPage);
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return authDetails;
	}
	
	/***
	 * Return : Get authors from the short list table
	 */
	public static List<Author> getShortListedAuthors(){
		List<Author> shortListedAuthors = new ArrayList<>();
		
		try{
		AuthorDetailsDAO dao=new AuthorDetailsDAO();
		shortListedAuthors = dao.getShortListedAuthors();
		/*for (Author a : shortListedAuthors){
			System.out.println("short listed authors : " + a.getAuthName());
		}*/
		return shortListedAuthors;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
	}
	
	/***
	 * 
	 * @param author
	 * Return void => Deletes the author using author name.
	 */
	public static void delShortListedAuthors(String author,boolean boolVal){
		
		try{
		AuthorDetailsDAO dao=new AuthorDetailsDAO();
		dao.deleteShortListedAuthors(author, boolVal);
		System.out.println(" Record deleted successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	/***
	 * 
	 * @param author
	 * Adds an author to the short list table
	 */
	public static void addShortListedAuthors(String author){
		
		try{
		AuthorDetailsDAO dao=new AuthorDetailsDAO();
		dao.AddShortListedAuthor(author);
		System.out.println(" Record added successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	
	/***
	 * 
	 * @param author
	 * Get count of confereces and journals.
	 */
	public String getCountOfJournalsAndConf(String author){
		
		try{
		
		AuthorDetailsDAO dao=new AuthorDetailsDAO();
		String counts = dao.getNumberOfConferencesandJournals(author);
		//System.out.println(counts);
		return counts;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
	}
	
	public List<SearchAuthMapper> searchForAllCategory(String conferenceName,String journalName,Author auth,String choice,int pgMin,int pgMax) throws Exception
	{
		System.out.println("Choice selected "+auth.getServedChoice());
		Author authVerified=new Author();
		try{
			if(conferenceName !=null)
			{
				if(conferenceName.trim().length()==0)
					conferenceName=null;
				else
					conferenceName=conferenceName.trim().toUpperCase();
			}
			
			if(journalName!=null)
			{
				if(journalName.trim().length()==0)
					journalName=null;
				else
					journalName=journalName.trim().toUpperCase();
			}
			if(auth.getAuthName()!=null)
			{
				if(auth.getAuthName().trim().length()==0)
					authVerified.setAuthName(null);
				else
					authVerified.setAuthName(auth.getAuthName().trim().toUpperCase());
			}
			if(auth.getYear()!=null)
			{
				if(auth.getYear().trim().length()==0)
					authVerified.setYear(null);
				else if(auth.getYear().trim().length()==4)
					authVerified.setYear(auth.getYear().trim());
					
			}
			if(auth.getYearOption()==null)
			{
				authVerified.setYearOption(null);;
			}
			
			if(auth.getTitle()!=null)
			{
				if(auth.getTitle().trim().length()==0)
					authVerified.setTitle(null);
				else
					authVerified.setTitle(auth.getTitle().trim().toUpperCase());
			}
			if(choice==null)
			{
				choice="OR";
			}
			if(auth.getNumberOfPublications()>0)
			{
				authVerified.setNoOfPublications(auth.getNoOfPublications());
			}
			else
			{
				authVerified.setNoOfPublications(0);
			}
			if(auth.getNoOfJournals()>0)
			{
				authVerified.setNoOfJournals(auth.getNoOfJournals());
				
			}
			else
			{
				authVerified.setNoOfJournals(0);
			}
			if(auth.getServedConference()!=null)
			{
				if(auth.getServedConference().trim().length()==0)
					authVerified.setServedConference(null);
				else
					authVerified.setServedConference(auth.getServedConference());
			}
			if(auth.getServedTitleOption()!=null)
			{
				if(auth.getServedConference().trim().length()==0)
					authVerified.setServedTitleOption(null);
				else
					authVerified.setServedTitleOption(auth.getServedTitleOption().toUpperCase());
			}
			if(auth.getServedChoice()!=null)
			{
				if(auth.getServedChoice().trim().length()==0)
					authVerified.setServedChoice(null);
				else
					authVerified.setServedChoice(auth.getServedChoice().toUpperCase());
			}
			
			AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
			return dao.callProc(conferenceName, journalName, auth, choice, pgMin, pgMax);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
			
		}
		
	}
	
}
		

	
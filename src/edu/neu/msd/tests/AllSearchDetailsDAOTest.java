package edu.neu.msd.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import edu.neu.msd.dao.AllSearchDetailsDAO;
import edu.neu.msd.dto.Author;
import edu.neu.msd.dto.SearchAuthMapper;

public class AllSearchDetailsDAOTest {


	public void conferenceNameTest(){
		System.out.println("*******************CONFERENCE TEST**************************");
		String conference="OOPSLA";
		Author auth=new Author();
		AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
		Connection con=dao.getCon();
		List<SearchAuthMapper> mySearch;
		try {
			mySearch=dao.callAllSearch(conference, null, auth, "OR", 1, 10 );
			assertEquals(10, mySearch.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void journalTest(){
		System.out.println("*******************JOURNAL TEST**************************");
		String conference=null;
		String journalName="IEEE Comp. Int. Mag.";
		Author auth=new Author();
		AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
		Connection con=dao.getCon();
		List<SearchAuthMapper> mySearch;
		try {
			mySearch=dao.callAllSearch(null, journalName, auth, "OR", 1, 10 );
			assertEquals(10, mySearch.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	@Test
	public void conferenceJournalTest(){
		System.out.println("*******************CONFERENCE JOURNAL UNION TEST**************************");
		String conference="OOPSLA";
		String journalName="IEEE Comp. Int. Mag.";
		Author auth=new Author();
		AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
		Connection con=dao.getCon();
		List<SearchAuthMapper> mySearch;
		try {
			mySearch=dao.callAllSearch(conference, journalName, auth, "OR", 1, 10 );
			assertEquals(10, mySearch.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
public void conferenceJournalIntersectTest(){
		System.out.println("*******************CONFERENCE JOURNAL INTERSECT TEST**************************");
		String conference="OOPSLA";
		String journalName="IEEE Comp. Int. Mag.";
		Author auth=new Author();
		AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
		Connection con=dao.getCon();
		List<SearchAuthMapper> mySearch;
		try {
			mySearch=dao.callAllSearch(conference, journalName, auth, "AND", 1, 10 );
			assertEquals(10, mySearch.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
public void authSearchTest(){
		System.out.println("*******************AUTH SEARCH TEST WITH NAME**************************");
		String conference=null;
		String journalName=null;
		Author auth=new Author();
		auth.setAuthName("frank");
		AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
		Connection con=dao.getCon();
		List<SearchAuthMapper> mySearch;
		try {
			mySearch=dao.callAllSearch(conference, journalName, auth, "OR", 1, 10 );
			assertEquals(10, mySearch.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	
	
	
	
	@Test
	public void authTitleTest(){
			System.out.println("*******************AUTH SEARCH TEST WITH TITLE**************************");
			String conference=null;
			String journalName=null;
			Author auth=new Author();
			auth.setTitle("pointer");
			AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
			Connection con=dao.getCon();
			List<SearchAuthMapper> mySearch;
			try {
				mySearch=dao.callAllSearch(conference, journalName, auth, "OR", 1, 10 );
				assertEquals(10, mySearch.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	
	@Test
	public void authYearTest(){
			System.out.println("*******************AUTH YEAR TEST WITH TITLE**************************");
			String conference=null;
			String journalName=null;
			Author auth=new Author();
			auth.setYear("2015");
			auth.setYearOption("LEQ");
			auth.setTitle("pointer");
			AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
			Connection con=dao.getCon();
			List<SearchAuthMapper> mySearch;
			try {
				mySearch=dao.callAllSearch(conference, journalName, auth, "OR", 1, 10 );
				assertEquals(10, mySearch.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	
	
	@Test
	public void authYearNoOfPubsTest(){
			System.out.println("*******************AUTH YEAR TEST WITH TITLE AND NUMBER OF PUBS**************************");
			String conference=null;
			String journalName=null;
			Author auth=new Author();
			auth.setYear("2015");
			auth.setYearOption("LEQ");
			auth.setTitle("pointer");
			auth.setNoOfPublications(10);
			
			AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
			Connection con=dao.getCon();
			List<SearchAuthMapper> mySearch;
			try {
				mySearch=dao.callAllSearch(conference, journalName, auth, "OR", 1, 10 );
				assertEquals(10, mySearch.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	
	@Test
	public void authNoOfJournalsMultipleJournals()
	{
		System.out.println("*******************AUTH NUMBER OF JOURNALS MULTI JOURNALS**************************");
		String conference=null;
		String journalName=null;
		Author auth=new Author();
		auth.setYear("2015");
		auth.setYearOption("LEQ");
		auth.setTitle("pointer");
		auth.setNoOfJournals(10);
		
		AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
		Connection con=dao.getCon();
		List<SearchAuthMapper> mySearch;
		try {
			mySearch=dao.callAllSearch(conference, journalName, auth, "AND", 1, 10 );
			assertEquals(3, mySearch.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void authNoOfJournalsOnly()
	{
		System.out.println("*******************AUTH NUMBER OF JOURNALS ONLY**************************");
		String conference=null;
		String journalName=null;
		Author auth=new Author();
		auth.setYear("2015");
		auth.setYearOption("LEQ");
		auth.setTitle("pointer");
		auth.setNoOfJournals(10);
		auth.setNoOfPublications(10);
		AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
		Connection con=dao.getCon();
		List<SearchAuthMapper> mySearch;
		try {
			mySearch=dao.callAllSearch(conference, journalName, auth, "OR", 1, 100 );
			assertEquals(4, mySearch.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void authFrankOOPSLA()
	{
		System.out.println("*******************FRANK OOPSLA**************************");
		String conference="OOPSLA";
		String journalName=null;
		Author auth=new Author();
		auth.setAuthName("Frank");
		AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
		Connection con=dao.getCon();
		List<SearchAuthMapper> mySearch;
		try {
			mySearch=dao.callAllSearch(conference, journalName, auth, "OR", 1, 100 );
			assertEquals(100, mySearch.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void authFrankWithCheckPrevServed()
	{
		System.out.println("*******************FRANK OOPSLA**************************");
		String conference="OOPSLA";
		String journalName=null;
		Author auth=new Author();
		auth.setAuthName("Frank Tip");
		auth.setServedChoice("YES");
		auth.setServedConference("ECOOP,OOPSLA");
		auth.setServedTitleOption("p");
		AllSearchDetailsDAO dao=new AllSearchDetailsDAO();
		Connection con=dao.getCon();
		List<SearchAuthMapper> mySearch;
		try {
			mySearch=dao.callAllSearch(conference, journalName, auth, "OR", 1, 100 );
			assertEquals(0, mySearch.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

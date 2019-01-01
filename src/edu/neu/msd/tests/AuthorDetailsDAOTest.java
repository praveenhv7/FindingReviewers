package edu.neu.msd.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import edu.neu.msd.dao.AuthorDetailsDAO;
import edu.neu.msd.datasource.DataSource;
import edu.neu.msd.dto.Author;
import edu.neu.msd.dto.AuthorsMapper;
import edu.neu.msd.dto.CommonAuthorMapper;
import edu.neu.msd.dto.PublicationMapper;
import org.junit.Test;

public class AuthorDetailsDAOTest {
	
	

	
	public void testGetAuthorsByName() {
AuthorDetailsDAO dao = new AuthorDetailsDAO();

		Author author = new Author();
		author.setAuthName("Craig Chambers");
		author.setTypeOfPublication("BOTH");
		assertNotNull("Test Case Passed",dao.getAuthorsByName(author, 1, 100));
		
		// Does not exist in database
		author = new Author();
		author.setAuthName("xyz");
		
		assertNotNull("Test Case Passed", dao.getAuthorsByName(author, 1, 100));
		
		author = new Author();
		author.setAuthName("Adele Goldberg");
		author.setConfereceName("OOPSLA");
		author.setJournalName("IEEE Software");
		author.setTypeOfPublication("BOTH");
		assertNotNull("Test Case Passed", dao.getAuthorsByName(author, 1, 100));
	}

	@Test
	public void testGetShortListedAuthors() {
		AuthorDetailsDAO dao = new AuthorDetailsDAO();
		List<Author> authorList = new ArrayList();
		List<Author> authorList2 = new ArrayList();
		dao.AddShortListedAuthor("Craig Chambers");
		authorList = dao.getShortListedAuthors();
		assertNotNull("Test Case Passed",authorList);
		
		
		dao.deleteShortListedAuthors("Craig Chambers", false);
		authorList2 = dao.getShortListedAuthors();
		
		assertEquals("Test Case Passed",authorList.size(),authorList2.size() + 1);
		
		
		dao.deleteShortListedAuthors(null, true);
		authorList = dao.getShortListedAuthors();
		assertEquals("Test Case Passed",0,authorList.size());
		
	}

	@Test
	public void testDeleteShortListedAuthors() {
		AuthorDetailsDAO dao = new AuthorDetailsDAO();
		List<Author> authorList = new ArrayList<Author>();
		List<Author> authorList2 = new ArrayList<Author>();
		authorList = dao.getShortListedAuthors();
		assertNotNull("Test Case Passed",authorList);
		
		dao.deleteShortListedAuthors("Craig Chambers", false);
		authorList2 = dao.getShortListedAuthors();
		assertEquals("Test Case Passed",authorList.size(),authorList2.size() + 1);
		
		
		dao.deleteShortListedAuthors(null, true);
		authorList = dao.getShortListedAuthors();
		assertEquals("Test Case Passed",0,authorList.size());
	}

	@Test
	public void testAddShortListedAuthor() {
		AuthorDetailsDAO dao = new AuthorDetailsDAO();
		List<Author> authorList = new ArrayList<Author>();
		List<Author> authorList2 = new ArrayList<Author>();
		authorList = dao.getShortListedAuthors();
		assertNotNull("Test Case Passed",authorList);
		
		dao.AddShortListedAuthor("Craig Chambers");
		authorList2 = dao.getShortListedAuthors();
		assertEquals("Test Case Passed",authorList.size()+1,authorList2.size());
		
		
		dao.AddShortListedAuthor("");
		authorList2 = dao.getShortListedAuthors();
		assertEquals("Test Case Passed",authorList.size()+1,authorList2.size());
	}

	@Test
	public void testGetNumberOfConferencesandJournals() {
		AuthorDetailsDAO dao = new AuthorDetailsDAO();
		String totalPublications;
		totalPublications = dao.getNumberOfConferencesandJournals("Craig Chambers");
		
		assertEquals("Test Case", "Conference Publications: 33  Journal Publications: 0",totalPublications);
		
		totalPublications = dao.getNumberOfConferencesandJournals("Frank Tip");
		assertNotEquals("Test Case", "33 2",totalPublications);}
	
	
	@Test
	public void testGetAuthorAffiliation()
	{
		Author a1 = new Author();
		AuthorDetailsDAO dao = new AuthorDetailsDAO();
		a1.setAuthName("Andrew A. Chien");
		assertEquals("Test Case", dao.getAuthorAffiliation(a1.getAuthorName())," University of Chicago");
		
	}
	
	@Test
	public void testGetAuthorHomepage()
	{
		Author a1 = new Author();
		AuthorDetailsDAO dao = new AuthorDetailsDAO();
		a1.setAuthName("Andrew A. Chien");
		System.out.print(dao.getAuthorHomepage(a1.getAuthorName()));
		assertEquals("Test Case", dao.getAuthorHomepage(a1.getAuthorName()),"https://www.cs.uchicago.edu/directory/andrew-chien/");
		
	}
	
}
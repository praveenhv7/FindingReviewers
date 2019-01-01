package edu.neu.msd.tests;

import static org.junit.Assert.*;


import org.junit.Test;

import edu.neu.msd.dao.ConferenceDetailsDAO;
import edu.neu.msd.dto.Author;
import edu.neu.msd.datasource.DataSource;
import edu.neu.msd.dto.Author;
import edu.neu.msd.dto.AuthorsMapper;
import edu.neu.msd.dto.CommonAuthorMapper;
import edu.neu.msd.dto.PublicationMapper;

public class ConferenceDetailsDAOTest {

	@Test
	public void testGetConferenceDetails() {
		ConferenceDetailsDAO cao = new ConferenceDetailsDAO();
		Author author = new Author();
		
		author.setAuthName("Frank Tip");
		author.setConfereceName("OOPSLA");
		assertNotNull("Get Authors for given conference", cao.getConferenceDetails(author, 1, 100));
		
		author.setAuthName("frank tip");
		assertNotNull("Get Authors for given conference", cao.getConferenceDetails(author, 1, 100));
		
		author.setAuthName("Craig Chambers");
		
		assertNotNull("Get Authors for given conference", cao.getConferenceDetails(author, 1, 100));
		
		
	}

	

	@Test
	public void testGetAuthorsbyConference() {
		Author a = new Author();
		Author author = new Author();
		ConferenceDetailsDAO cao = new ConferenceDetailsDAO();
		a.setConfereceName("ECOOP");
		a.setTitle("reusable");
		a.setYear("1992");
				assertEquals("Test Case passed",4,cao.getAuthorsbyConference(a, 1, 100).size());
		
		author.setConfereceName("OOPSLA");
		assertNotNull("Get Authors for given conference", cao.getAuthorsbyConference(author, 1, 100));
		
		author.setConfereceName("ecoop");
		author.setTitle("reusable");
		author.setYear("1992");
		assertNotNull("Get Authors for given conference", cao.getAuthorsbyConference(author, 1, 100));
		
		a.setConfereceName("ECOOP");
		a.setTitle("reusable");
		

		assertEquals("Test Case passed",4,cao.getAuthorsbyConference(a, 1, 100).size());
		
		author.setConfereceName("OOPSLA");
		author.setTitle("WSNs");
		assertEquals("Test Case passed",0,cao.getAuthorsbyConference(author, 1, 100).size());
		
	}

}
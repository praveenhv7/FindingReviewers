package edu.neu.msd.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.neu.msd.dto.Author;
import edu.neu.msd.dao.JournalDetailsDAO;
import edu.neu.msd.dto.AuthorsMapper;


public class JournalDetailsDAOTest {

	@Test
	public void testGetJournalDetails() {
		JournalDetailsDAO jao = new JournalDetailsDAO();
		Author author = new Author();
		
		author.setAuthName("Frank Tip");
		author.setJournalName("IEEE Access");
		assertNotNull("Get Authors for given journal", jao.getJournalDetails(author, 1, 100));
		
		author.setAuthName("frank tip");
		assertNotNull("Get Authors for given journal", jao.getJournalDetails(author, 1, 100));
		
		author.setAuthName("Craig Chambers");
		assertNotNull("Get Authors for given journal", jao.getJournalDetails(author, 1, 100));
		
		//author.setAuthName("Rushabh");
		//assertNull("Get Authors for given journal", jao.getJournalDetails(author, 1, 100));
	}

	

	@Test
	public void testGetAuthorsbyJournal() {
		JournalDetailsDAO jao = new JournalDetailsDAO();
		Author author = new Author();
		
		author.setJournalName("eat");
		assertEquals("Get Authors for given journal", 0,jao.getAuthorsbyJournal(author, 1, 100).size());
		
		author.setJournalName("IEEE Access");
		assertNotNull("Get Authors for given journal", jao.getAuthorsbyJournal(author, 1, 100));
		
		
		author.setJournalName("IEEE Access");
		author.setYear("2016");
		assertNotNull("Get Authors for given journal", jao.getAuthorsbyJournal(author, 1, 100));
		
		author.setJournalName("IEEE Access");
		author.setYear("2016");
		assertNotNull("Get Authors for given journal", jao.getAuthorsbyJournal(author, 1, 100));

	}

}
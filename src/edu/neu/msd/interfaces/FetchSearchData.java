package edu.neu.msd.interfaces;

import java.util.List;

import edu.neu.msd.dto.Author;
import edu.neu.msd.dto.Conference;
import edu.neu.msd.dto.Journal;

public interface FetchSearchData {
	
	public List<Author> getAuthorsFromDB(String authName);
	
	public List<Author> getCoAuthorFromDB(String coAuthName);
	
	public List<Journal> getJournalDetailsFromDB(String joournalName, String volume, String publisher, String publicationTitle);
	
	public Object getAuthorDetails(String authName, String id);
	
	public List<Conference> getConferenceByAuthor(Object author);
	
	public  List<Journal> getJournalByAuthor(Object author);
	
	public List<Conference> getConferenceDetailsFromDB(String confereceName);

}

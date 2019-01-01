package edu.neu.msd.interfaces;
import java.util.List;

import edu.neu.msd.dto.Author;
import edu.neu.msd.dto.Journal;

public interface SearchByJournalDetailsI extends FilterBy {
	
	public List<Journal> getJournalsByJournalDetails(String journalName, String journalVolume, String publicationTitle, String publisherName);
	
	public List<Author> getAuthorByJournals(List<Journal> list);
	// then we can filter using filter interface functions
}

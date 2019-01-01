package edu.neu.msd.interfaces;
import java.util.List;

import edu.neu.msd.dto.Author;
import edu.neu.msd.dto.Conference;

public interface SearchByConferenceDetailsI extends FilterBy {
	
	public List<Conference> getConferenceByConferenceName(String conferenceName);
	
	public List<Author> getAllAuthorForConferences(List<Conference> list);
	// then we can filter using filter interface functions
	
}

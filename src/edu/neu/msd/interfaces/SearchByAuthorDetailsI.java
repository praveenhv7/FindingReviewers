package edu.neu.msd.interfaces;
import java.util.List;

import edu.neu.msd.dto.Author;

public interface SearchByAuthorDetailsI extends FilterBy {
	
	public List<Author> searchByAuthorDetails(String name);
	
	public List<Author> searchCoAuthors(String authorName);
}

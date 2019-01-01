package edu.neu.msd.interfaces;
import java.util.Date;
import java.util.List;

import edu.neu.msd.dto.Author;

/**
 * 
 */

/**
 * @author Harsh
 *
 */
public interface FilterBy{
	
	public List<Author> filterByDateRange(Date from, Date to);
	
	public List<Author> filterByDatePublished(Date dateOfPublication);
	
	public List<Author> filterByNumberOfPublications(int minimumNumberOfPublication);
	
	public List<Author> filterByKeywords(String keywords);
}

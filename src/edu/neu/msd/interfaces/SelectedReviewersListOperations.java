package edu.neu.msd.interfaces;
import java.util.List;

import edu.neu.msd.dto.Author;

public interface SelectedReviewersListOperations {
	
	public void removeSelectedReviewer(String name, String id);
	
	public void notifySelectedReviewers( List<String> listReviewerEmails, String senderEmail);
	
	public Object viewSelectedReviewerProfile(String name, String id);
	
	public List<Author> addReviwerToList(Object author);
}

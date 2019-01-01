package edu.neu.msd.dto;

public class SearchAuthor {

	private String authorName;
	private Integer countPublications;
	private Integer countJournals;
	
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Integer getCountPublications() {
		return countPublications;
	}

	public void setCountPublications(Integer countPublications) {
		this.countPublications = countPublications;
	}

	public Integer getCountJournals() {
		return countJournals;
	}

	public void setCountJournals(Integer countJournals) {
		this.countJournals = countJournals;
	}
	
}

package edu.neu.msd.dto;

public class Author {

	private String authName;
	private int committeServed;
	private int noOfPublications;
	private String title;
	private String typeOfPublication;
	private String year;
	private String yearOption;
    private String confereceName;
	private String journalName;
	private int noOfJournals;
	private String servedConference;
	private String servedTitleOption;
	private String servedChoice;
	
	
	
	public String getServedConference() {
		return servedConference;
	}

	public void setServedConference(String servedConference) {
		this.servedConference = servedConference;
	}

	public String getServedTitleOption() {
		return servedTitleOption;
	}

	public void setServedTitleOption(String servedTitleOption) {
		this.servedTitleOption = servedTitleOption;
	}

	public String getServedChoice() {
		return servedChoice;
	}

	public void setServedChoice(String servedChoice) {
		this.servedChoice = servedChoice;
	}

	public int getNoOfJournals() {
		return noOfJournals;
	}

	public void setNoOfJournals(int noOfJournals) {
		this.noOfJournals = noOfJournals;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getConfereceName() {
		return confereceName;
	}

	public void setConfereceName(String confereceName) {
		this.confereceName = confereceName;
	}

	public String getJournalName() {
		return journalName;
	}

	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getCommitteServed() {
		return committeServed;
	}

	public void setCommitteServed(int committeserved) {
		this.committeServed =  committeserved;
	}

	public int getNoOfPublications() {
		return noOfPublications;
	}

	public void setNoOfPublications(int noOfPublications) {
		this.noOfPublications = noOfPublications;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTypeOfPublication() {
		return typeOfPublication;
	}

	public void setTypeOfPublication(String typeOfPublication) {
		this.typeOfPublication = typeOfPublication;
	}


	public String getAuthorName(){
		return authName;
	}

	public int getNumberOfPublications(){
		return noOfPublications;
	}
	
	public String getYearOption() {
		return yearOption;
	}

	public void setYearOption(String yearOption) {
		this.yearOption = yearOption;
	}
}

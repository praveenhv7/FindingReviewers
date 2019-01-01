package edu.neu.msd.dto;

import java.util.List;

public class Titles {
	
	private String titleName;
	private List<Author> authors;
	private String conferenceName;
	
	public String getTitlename(){
		return titleName;
	}
	
	public List<Author> getTitleAuthors(){
		return null;
	}
	
	public String getConferenceName(){
		return conferenceName;
	}
}

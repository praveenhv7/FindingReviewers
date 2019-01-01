package edu.neu.msd.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conference {
	private String conferenceName;
	private String year;
	private String location;
	private List<String> titles;
	
	public Conference(String name,String year, String location) {
		titles = new ArrayList<>();
		conferenceName = name;
		this.year = year;
		this.location = location;	
		
	}
	public String getConferenceName(){
		return conferenceName;
	}
	
	public String getYear(){
		return year;
	}
	
	public String getLocation(){
		return location;
	}
	
	public List<String> getTitles(){
		return titles;
	}
	
	public void addTitle(String title)
	{
		titles.add(title);
	}
	
	public int getNumberofPublications(){
		return titles.size();
	}
}

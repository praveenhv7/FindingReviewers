package edu.neu.msd.dto;

import java.util.ArrayList;
import java.util.List;

public class Publication {

	public String title;
	public List<String> authors;
	public String year;
	public String pages;
	public String crossref;
	public String booktitle;
	public List<String> ee;
	public String url;
	public String key_pub;
	public String mdate;
	
	public String getTitle() {
		return title;
	}
	public String getKey_pub() {
		return key_pub;
	}
	public void setKey_pub(String key_pub) {
		this.key_pub = key_pub;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getCrossref() {
		return crossref;
	}
	public void setCrossref(String crossref) {
		this.crossref = crossref;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public List<String> getEe() {
		return ee;
	}
	public void setEe(List<String> ee) {
		this.ee = ee;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void clear() {
		// TODO Auto-generated method stub
		this.title="";
		this.authors.clear();
		this.booktitle="";
		this.crossref="";
		//this.ee.clear();
		this.pages="";
		this.url="";
		this.year="";
	} 
	
	public Publication() {
		// TODO Auto-generated constructor stub
		 this.title="";
		 this.authors=new ArrayList<String>();
		 this.year="";
		 this.pages="";
		 this.crossref="";
		 this.booktitle="";
		 this.ee=null;
		 this.url="";
		
		
	}
	
}

/**
 * <title>Modeling Evaluation Of Continuous Queries on SlidingWindows.</title>
<pages>632-637</pages>
<year>2006</year>
<crossref>conf/icdm/2006w</crossref>
<booktitle>ICDM Workshops</booktitle>
<ee>http://dx.doi.org/10.1109/ICDMW.2006.119</ee>
<ee>http://doi.ieeecomputersociety.org/10.1109/ICDMW.2006.119</ee>
<url>db/conf/icdm/icdmw2006.html#DaniG06</url>
 */
 

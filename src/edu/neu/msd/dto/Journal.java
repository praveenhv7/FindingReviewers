package edu.neu.msd.dto;

import java.util.*;

public class Journal {
	
	private String journalName;
	private String year;
	private String volume;
	private List<Titles> titles;
	private String pages;
	private String mdate;
	private String numberArticle;
	private String articleTitle;
	/**
	 * @return the journalName
	 */
	public String getJournalName() {
		return journalName;
	}
	/**
	 * @param journalName the journalName to set
	 */
	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the volume
	 */
	public String getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}
	/**
	 * @return the titles
	 */
	public List<Titles> getTitles() {
		return titles;
	}
	/**
	 * @param titles the titles to set
	 */
	public void setTitles(List<Titles> titles) {
		this.titles = titles;
	}
	/**
	 * @return the pages
	 */
	public String getPages() {
		return pages;
	}
	/**
	 * @param pages the pages to set
	 */
	public void setPages(String pages) {
		this.pages = pages;
	}
	/**
	 * @return the mdate
	 */
	public String getMdate() {
		return mdate;
	}
	/**
	 * @param mdate the mdate to set
	 */
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	/**
	 * @return the numberArticle
	 */
	public String getNumberArticle() {
		return numberArticle;
	}
	/**
	 * @param numberArticle the numberArticle to set
	 */
	public void setNumberArticle(String numberArticle) {
		this.numberArticle = numberArticle;
	}
	/**
	 * @return the articleTitle
	 */
	public String getArticleTitle() {
		return articleTitle;
	}
	/**
	 * @param articleTitle the articleTitle to set
	 */
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

}

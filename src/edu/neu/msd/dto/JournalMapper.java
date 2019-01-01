package edu.neu.msd.dto;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class JournalMapper extends Journal implements SQLData,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String sql_type="JOURNAL_OBJ_TYPE";
	@Override
	public String getSQLTypeName() throws SQLException {
		// TODO Auto-generated method stub
		return sql_type;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		// TODO Auto-generated method stub
		this.sql_type = typeName;
		setArticleTitle(stream.readString());
		setPages(stream.readString());
		setYear(stream.readString());
		setMdate(stream.readString());
		setJournalName(stream.readString());
		setVolume(stream.readString());
		setNumberArticle(stream.readString());		
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		// TODO Auto-generated method stub
		stream.writeString(getArticleTitle());
		stream.writeString(getPages());
		stream.writeString(getYear());
		stream.writeString(getMdate());
		stream.writeString(getJournalName());
		stream.writeString(getVolume());
		stream.writeString(getNumberArticle());
		
	}

}
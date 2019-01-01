package edu.neu.msd.dto;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class SearchAuthMapper extends SearchAuthor implements Serializable,SQLData {

	
	
	String sql_type = "SEARCH_RESULT_OBJECT";
	
	@Override
	public String getSQLTypeName() throws SQLException {
		// TODO Auto-generated method stub
		return sql_type;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		// TODO Auto-generated method stub
		this.sql_type=typeName;
		setAuthorName(stream.readString());
		setCountPublications(stream.readInt());
		setCountJournals(stream.readInt());
		
		
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		// TODO Auto-generated method stub
		stream.writeString(getAuthorName());
		stream.writeInt(getCountPublications());
		stream.writeInt(getCountJournals());
		
	}

	
	

}

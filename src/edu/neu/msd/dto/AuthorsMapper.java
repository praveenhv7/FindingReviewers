package edu.neu.msd.dto;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;


/*author varchar2(500),
title varchar2(500),
year varchar2(20),
key_details varchar2(500), 
conf_journal_name varchar2(500),
number_of_publications varchar2(500)*/

public class AuthorsMapper extends Author implements SQLData,Serializable{

	String sql_type="AUTHOR_OBJ_TYPE";
	@Override
	public String getSQLTypeName() throws SQLException {
		// TODO Auto-generated method stub
		return sql_type;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		// TODO Auto-generated method stub
		this.sql_type = typeName;
		setAuthName(stream.readString());
		setTitle(stream.readString());
		setYear(stream.readString()); 
		stream.readString();// skipping reading of key_details 
		setTypeOfPublication(stream.readString());
		setNoOfPublications(Integer.parseInt(stream.readString()));
		
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		// TODO Auto-generated method stub
		
		stream.writeString(getAuthName());
		stream.writeString(getTitle());
		stream.writeString(getYear());
		stream.writeString(null);
		stream.writeString(getTypeOfPublication());
		stream.writeString(Integer.toString(getNoOfPublications()));
		
	}

}

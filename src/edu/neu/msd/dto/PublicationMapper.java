package edu.neu.msd.dto;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class PublicationMapper extends Publication implements SQLData,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String sql_type="PUBLICATIONS_OBJ_TYPE";
	@Override
	public String getSQLTypeName() throws SQLException {
		// TODO Auto-generated method stub
		return sql_type;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		// TODO Auto-generated method stub
		this.sql_type = typeName;
		setTitle(stream.readString());
		setPages(stream.readString());
		setYear(stream.readString());
		setMdate(stream.readString());
		//setBooktitle(stream.readString());
		
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		// TODO Auto-generated method stub
		stream.writeString(getTitle());
		stream.writeString(getPages());
		stream.writeString(getYear());
		stream.writeString(getMdate());
//		stream.writeString(getBooktitle());
		
	}

}

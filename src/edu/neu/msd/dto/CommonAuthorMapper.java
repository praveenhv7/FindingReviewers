package edu.neu.msd.dto;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class CommonAuthorMapper extends Author implements SQLData,Serializable {

	
	String sql_type = "AUTHOR_PUBS_DETAILS_OBJ";
	@Override
	public String getSQLTypeName() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		// TODO Auto-generated method stub
		this.sql_type = typeName;
		setAuthName(stream.readString());
		
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		// TODO Auto-generated method stub
		stream.writeString(getAuthName());
	}
	

}

package edu.neu.msd.interfaces;
import java.sql.Connection;
import edu.neu.msd.dto.ConnectionParams;

public interface DatabaseConnection {
	
	public ConnectionParams getDatabaseConnectionParams(String filepath);
	
	public Connection getDatabaseConnection(ConnectionParams params);

}

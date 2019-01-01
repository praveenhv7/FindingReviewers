package edu.neu.msd.datasource;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.xml.bind.helpers.DefaultValidationEventHandler;

public class DataSource {
	
	private Connection con;
	private String url;
	private String userName;
	private String password;
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public DataSource()
	{
		InputStream inputStream;
	
		try {
			Properties prop = new Properties();
			String propFileName = "edu\\neu\\msd\\properties\\dbConnectionData.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			
 
			
			this.url = prop.getProperty("URL");
			this.userName = prop.getProperty("userName");
			this.password = prop.getProperty("password");
			System.out.println("URL "+this.url);
			this.con=DriverManager.getConnection(url, userName, password);
			
			System.out.println("Connection establied ");
			
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	

	}
	
}

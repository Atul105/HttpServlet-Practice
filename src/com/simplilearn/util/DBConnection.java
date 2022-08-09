package com.simplilearn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
	private Connection connection;
	
	//data source config
	private String URL = "jdbc:mysql://localhost:3306/ecomwebapp";
	private String DRIVER = "com.mysql.cj.jdbc.Driver";
	private String USERNAME = "root";
	private String PASSWORD = "Simplilearn";
	
	public DBConnection() throws ClassNotFoundException, SQLException {
		
		//register DRIVER
		Class.forName(DRIVER);
		
		//create DRIVER
		this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		
	}
	//get connection
	public Connection getConnection() {
		return this.connection;
	}
	
	//close connection
	public void releaseConnection() throws SQLException {
		if(connection != null) {
			this.connection.close();
		}
	}
}

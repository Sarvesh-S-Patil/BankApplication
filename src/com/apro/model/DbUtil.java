package com.apro.model;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {
	
	private Statement statement;
	private Connection connection;	
	
	public void connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
			this.statement = connection.createStatement();
			
			System.out.println("Connection Successful");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Statement getStatement() {
		return statement;
	}

	public Connection getConnection() {
		return connection;
	}

	
	

}

package com.apro.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import com.mysql.cj.xdevapi.Result;

public class AdminLogin {
	Connection connection;
	
	
	
	public AdminLogin(Connection connection) {
		super();
		this.connection=connection;
	}



	public boolean verifyLogin(String username,String password) {
		String query = "select * from admin where username= ? and password = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2,password);
			ResultSet resultSet = preparedStatement.executeQuery();
			//System.out.println(resultSet.next());
			return resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public long getAdminId(String username,String password) {
		String query = "select adminid from admin where username= ? and password = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2,password);
			ResultSet resultSet = preparedStatement.executeQuery();
			Long adminId=(long) 0;
			if(resultSet.next()) {
				adminId=resultSet.getLong("adminId");
			}
			return adminId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}

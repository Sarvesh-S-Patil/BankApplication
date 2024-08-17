package com.apro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerLogin {
	
	Connection connection;
	
	
	
	public CustomerLogin(Connection connection) {
		super();
		this.connection=connection;
	}



	public boolean verifyLogin(String username,String password) {
		String query = "select * from customer where emailaddress= ? and password = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2,password);
			ResultSet resultSet = preparedStatement.executeQuery();
		
			return resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public long getCustomerId(String username, String password) {
        String query = "SELECT customerid FROM customer WHERE emailaddress = ? AND password = ?";
        long customerId = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password); 

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    customerId = resultSet.getLong("customerId");
                }
            }
        } catch (SQLException e) {
        
            e.printStackTrace(); 
        }

        return customerId;
    }

}

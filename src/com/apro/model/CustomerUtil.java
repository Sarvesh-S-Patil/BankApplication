package com.apro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.apro.exception.CustomerNotFoundException;

public class CustomerUtil {
	
	Connection connection;
	
	public CustomerUtil(Connection connection) {
		this.connection=connection;
	}
	
	public void addCustomer(String firstName,String lastName,String email,String password,long adminId) {
		String query = "Insert into customer(`firstname`,`lastname`,`emailaddress`,`password`,`adminId`) values (?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			preparedStatement.setLong(5, adminId);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Customer getCustomerById(Long customerId) {
		String query = "Select * from customer where customerid = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, customerId);
			ResultSet resultSet= preparedStatement.executeQuery();
			if(resultSet.next()) {
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				String email=resultSet.getString(4);
				String password=resultSet.getString(5);
				long adminId=resultSet.getLong(6);
				Customer customer = new Customer(customerId,firstName, lastName, email, password, adminId);
				return customer;
			}
			else {
				throw new CustomerNotFoundException();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (CustomerNotFoundException e) {
			// TODO: handle exception
			throw e;
		}
		return null;
		
	}
	
	
	public List<Customer> getCustomers(){
		String query = "Select * from customer limit 20;";
		List<Customer> customers = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet= preparedStatement.executeQuery();
			while(resultSet.next()) {
				long customerId = resultSet.getLong(1);
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				String email=resultSet.getString(4);
				String password=resultSet.getString(5);
				long adminId=resultSet.getLong(6);
				Customer customer = new Customer(customerId,firstName, lastName, email, password, adminId);
				customers.add(customer);
			}
			return customers;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<Account> getCustomerAccounts(long customerId) {
		String query = "SELECT *  FROM bank.account  left join bank.customer on account.customerid=customer.customerid where account.customerid=?";
		List<Account> accounts = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, customerId);
			ResultSet resultSet= preparedStatement.executeQuery();
			while(resultSet.next()) {
				String firstName = resultSet.getString("firstname");
				String lastName=resultSet.getString("lastname");
				String emailAddress=resultSet.getString("emailaddress");
				long accountId=resultSet.getLong("accountid");
				double balance=resultSet.getDouble("balance");
				Account account = new Account(firstName, lastName, emailAddress, accountId, balance);
				accounts.add(account);
			}
			return accounts;
		}	
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean updateCustomer(String firstName,String lastName,String password,long customerId) {
		String query = "update customer set firstname = ? , lastname =? , password = ? where customerid = ?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, password);
			preparedStatement.setLong(4, customerId);
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}

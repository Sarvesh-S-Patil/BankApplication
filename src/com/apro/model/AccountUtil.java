package com.apro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.mysql.cj.exceptions.CJConnectionFeatureNotAvailableException;


public class AccountUtil {
	private Connection connection;
	private long accountId;
	private double balance;
	private long customerid;

	public AccountUtil(Connection connection) {
		super();
		this.connection = connection;
	}
	
	
	public Long addAccount(double balance,long customerId) {
		String query = "Insert into account values(?,?,?)";
		PreparedStatement preparedStatement;
		this.balance=balance;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			long randomNumber = generateAccountNumber();
			while(isAccountPresent(randomNumber)) {
				randomNumber=generateAccountNumber();
			}
			accountId = randomNumber;
			preparedStatement.setLong(1,randomNumber);
			preparedStatement.setDouble(2, balance);
			preparedStatement.setLong(3, customerId);
			preparedStatement.execute();
			System.out.println("Account added successfully");
			return accountId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (long) -1;
	}
	
	public boolean isAccountPresent(long accountid) {
		String query = "Select * from account where accountid = ?";
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, accountid);
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		 
	}
	
	
	public long generateAccountNumber() {
        long min = 1000000000L;
        long max = 9999999999L;
        Random random = new Random();
        long randomNumber = min + (long) (random.nextDouble() * (max - min));
        return randomNumber;
	}
	
	public void deleteAccount(long accountId) {
		try {
			Statement statement=connection.createStatement();
			statement.execute("DELETE FROM account WHERE accountid = "+accountId+" ; ");
			System.out.println("Account Deleted : "+accountId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<Transaction> accountTransactions(long accountId){
		String query = "select * from transaction where SenderAccountId = ? or RecieverAccountId =? order by date asc limit 20;";
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1,accountId);
			preparedStatement.setLong(2, accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				long transactionId=resultSet.getLong(1);
				long senderAccountId=resultSet.getLong(2);
				long recieverAccountId=resultSet.getLong(3);
				String transactionType=resultSet.getString(4);
				double amount=resultSet.getDouble(5);
				Date sqlDate = resultSet.getDate(6);
				String date = (sqlDate != null) ? sqlDate.toString() : "";
				
				Transaction transaction = new Transaction(transactionId, senderAccountId, recieverAccountId, transactionType, amount, date);
				transactions.add(transaction);
			}
			return transactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}

	public boolean credit(long accountId,double amount) {
		if(amount<0) {
			return false;
		}
		double balance = getBalance(accountId);
		if(balance==-1)
			return false;
		balance+= amount;
		boolean status = setBalance(balance, accountId);
		if(status==false)
			return false;
		return true;
	}
	
	
	public boolean debit(long accountId,double amount) {
		double balance = getBalance(accountId);
		if(amount<0) {
			return false;
		}
		if(balance-amount<0) {
			return false;
		}
		balance-= amount;
		boolean status = setBalance(balance, accountId);
		if(status==false)
			return false;
		return true;
	}



	public double getBalance(long accountId) {
		try {
			String query = "select balance from account where accountId = ? ;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1,accountId);
			
			ResultSet resultSet =preparedStatement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getDouble(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	

	public boolean setBalance(double balance,long acccountId) {
		try {
			String query = "update account  set balance = ? where accountid = ? ; ";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1,balance);
			preparedStatement.setLong(2, acccountId);
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


	public long getCustomerid() {
		return customerid;
	}

	
	

}

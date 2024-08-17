package com.apro.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.apro.exception.AccountNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionUtil {

    Connection connection;

    public TransactionUtil(Connection connection) {
        super();
        this.connection = connection;
    }
    

	
	public boolean addTransaction(long senderAccountId,long recieverAccountId,String TransactionType,double amount) {
		
		AccountUtil accountUtil = new AccountUtil(connection);
		Date date= new Date();
		java.sql.Date date2 = new java.sql.Date(date.getTime());
		try {
			String creditQuery = "INSERT INTO transaction(recieveraccountid,transactiontype,amount,date) VALUES (?,?,?,?)";
			String debitQuery = "INSERT INTO transaction(senderAccountid,transactiontype,amount,date) VALUES (?,?,?,?)";
			String transferQuery = "INSERT INTO transaction(senderAccountid,recieveraccountid,transactiontype,amount,date) VALUES (?, ?,?,?,?)";
			connection.setAutoCommit(false);
			
			boolean status = true;
			if(TransactionType.equals("Credit")) {
				if(accountUtil.isAccountPresent(recieverAccountId)==false) {
					connection.rollback();
					throw new AccountNotFoundException();
				}
				PreparedStatement preparedStatement = connection.prepareStatement(creditQuery);
				preparedStatement.setLong(1, recieverAccountId);
				preparedStatement.setString(2, TransactionType);
				preparedStatement.setDouble(3, amount);
				preparedStatement.setDate(4, date2);
				int rowsAffected = preparedStatement.executeUpdate();
				if(rowsAffected==0) {
					connection.rollback();
					return false;
				}
				status =accountUtil.credit(recieverAccountId, amount);	
			}
			else if(TransactionType.equals("Debit")) {
				if(accountUtil.isAccountPresent(senderAccountId)==false) {
					connection.rollback();
					throw new AccountNotFoundException();
				}
				System.out.println("Inside debit");
				PreparedStatement preparedStatement = connection.prepareStatement(debitQuery);
				preparedStatement.setLong(1, senderAccountId);
				preparedStatement.setString(2, TransactionType);
				preparedStatement.setDouble(3, amount);
				preparedStatement.setDate(4, date2);
				int rowsAffected = preparedStatement.executeUpdate();
				if(rowsAffected==0) {
					connection.rollback();
					return false;
				}
					
				status=accountUtil.debit(senderAccountId, amount);
			}
			else {
				if(accountUtil.isAccountPresent(senderAccountId)==false) {
					connection.rollback();
					throw new AccountNotFoundException();
				}
				if(accountUtil.isAccountPresent(recieverAccountId)==false) {
					connection.rollback();
					throw new AccountNotFoundException();
				}
				PreparedStatement preparedStatement = connection.prepareStatement(transferQuery);
				preparedStatement.setLong(1, senderAccountId);
				preparedStatement.setLong(2, recieverAccountId);
				preparedStatement.setString(3, TransactionType);
				preparedStatement.setDouble(4, amount);
				preparedStatement.setDate(5, date2);
				int rowsAffected = preparedStatement.executeUpdate();
				if(rowsAffected==0) {
					connection.rollback();
					return false;
				}
						
				status=accountUtil.debit(senderAccountId, amount);
				if(status==false) {
					connection.rollback();
					return false;
				}	
				status=accountUtil.credit(recieverAccountId, amount);
			}
			if(status==false) {
				connection.rollback();
				return false;
			}
			else {
				connection.commit();
			}
			connection.setAutoCommit(true);
			return status;            
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (AccountNotFoundException e) {
			// TODO: handle exception
			throw e;
		}
		return false;
	}

    public List<Transaction> viewTransactions(String sortBy, String searchByAccountId) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM transaction");
        
        if (searchByAccountId != null && !searchByAccountId.isEmpty()) {
            queryBuilder.append(" WHERE senderAccountId = ? OR recieverAccountId = ?");
        }
        
        if (sortBy != null && !sortBy.isEmpty()) {
            queryBuilder.append(" ORDER BY ").append(sortBy);
        } else {
            queryBuilder.append(" ORDER BY date DESC");
        }
        
        queryBuilder.append(" LIMIT 20");
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            if (searchByAccountId != null && !searchByAccountId.isEmpty()) {
                preparedStatement.setLong(1, Long.parseLong(searchByAccountId));
                preparedStatement.setLong(2, Long.parseLong(searchByAccountId));
            }
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long transactionId = resultSet.getLong("transactionId");
                    long senderAccountId = resultSet.getLong("senderAccountId");
                    long recieverAccountId = resultSet.getLong("recieverAccountId");
                    String transactionType = resultSet.getString("transactionType");
                    double amount = resultSet.getDouble("amount");
                    Date sqlDate = resultSet.getDate("date");
                    String date = (sqlDate != null) ? sqlDate.toString() : "";

                    Transaction transaction = new Transaction(transactionId, senderAccountId, recieverAccountId, transactionType, amount, date);
                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}

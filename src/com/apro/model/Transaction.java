package com.apro.model;

public class Transaction {
    private long transactionId;
    private long senderAccountId;
    private long recieverAccountId;
    private String transactionType;
    private double amount;
    private String date; 
    
    
	public Transaction(long transactionId, long senderAccountId, long recieverAccountId, String transactionType,
			double amount, String date) {
		super();
		this.transactionId = transactionId;
		this.senderAccountId = senderAccountId;
		this.recieverAccountId = recieverAccountId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.date = date;
	}


	public long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}


	public long getSenderAccountId() {
		return senderAccountId;
	}


	public void setSenderAccountId(long senderAccountId) {
		this.senderAccountId = senderAccountId;
	}


	public long getRecieverAccountId() {
		return recieverAccountId;
	}


	public void setRecieverAccountId(long recieverAccountId) {
		this.recieverAccountId = recieverAccountId;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

   
}

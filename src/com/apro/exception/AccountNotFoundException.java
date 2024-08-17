package com.apro.exception;

public class AccountNotFoundException  extends RuntimeException{
	
	
	public String getMessage() {
		return "Account Cannot be found";
	}
}

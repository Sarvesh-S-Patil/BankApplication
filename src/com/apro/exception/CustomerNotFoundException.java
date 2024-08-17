package com.apro.exception;

public class CustomerNotFoundException extends RuntimeException {
	
	public String getMessage() {
		return "Customer does not exist";
	}

}

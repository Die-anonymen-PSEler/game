package com.retroMachines.util.lambda;

/**
 * This exception is thrown when the Json is damaged and therefore invalid.
 * 
 * @author RetroFactory
 * 
 */
public class InvalidJsonException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * creates a new instance of this class
	 */
	public InvalidJsonException() {
		super();
	}

	/**
	 * creates a new instance of this class
	 * @param errormessage errormessage why this exception occurred
	 */
	public InvalidJsonException(String errormessage) {
		super(errormessage);
	}
}

package com.retroMachines.util.lambda;

/**
 * This exception is thrown when the Json is damaged and therefore invalid.
 * 
 * @author RetroFactory
 * @version 1.0
 */
public class InvalidJsonException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of this class.
	 */
	public InvalidJsonException() {
		super();
	}

	/**
	 * Creates a new instance of this class.
	 * @param errormessage The error message why this exception occurred.
	 */
	public InvalidJsonException(String errormessage) {
		super(errormessage);
	}
}

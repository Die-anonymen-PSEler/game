package com.retroMachines.util.lambda;
/**
 * This exception is thrown when the Json is damaged and therefore invalid.
 * @author RetroFactory
 *
 */
public class InvalidJsonException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidJsonException() {
		super();
	}
	
	public InvalidJsonException(String errormessage) {
		super(errormessage);
	}
}

package com.retroMachines.util.lambda;
/**
 * This exception is thrown when the Json is damaged and therefore invalid.
 * @author RetroFactory
 *
 */
@SuppressWarnings("serial")
public class InvalidJsonException extends Exception {
	public InvalidJsonException() {
		super();
	}
	
	public InvalidJsonException(String errormessage) {
		super(errormessage);
	}
}

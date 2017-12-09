package com.sgs.exception;

/**
 * InvalidRangeException - Class for handling range exception
 */
public class InvalidRangeException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * InvalidRangeException - helper function for invalid range exception
	 */
	public InvalidRangeException(String message) {
		super(message);
	}

}

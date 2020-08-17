/**
 * 
 */
package com.mc.springboot.rest.error;

import org.springframework.http.HttpStatus;

/**
 * @author praveen
 *
 */
public class CityNotFoundException extends RuntimeException {
	HttpStatus errorStatus;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CityNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public CityNotFoundException(String message) {
		super(message);
	}

	public CityNotFoundException(HttpStatus error_status, String message) {
		super(message);
		errorStatus = error_status;
	}

	public HttpStatus getErrorStatus() {
		return errorStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param cause
	 */
	public CityNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CityNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

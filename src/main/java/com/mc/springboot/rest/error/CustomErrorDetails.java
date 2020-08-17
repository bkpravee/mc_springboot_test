/**
 * 
 */
package com.mc.springboot.rest.error;

import java.util.Date;

/**
 * @author praveen
 *
 */
public class CustomErrorDetails {

	private int status;
	private Date timestamp;
	private String message;

	public CustomErrorDetails(int code, Date timestamp, String message) {
		this.timestamp = timestamp;
		this.message = message;
		status = code;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}
	public int getStatus() {
		return status;
	}

	
	
}

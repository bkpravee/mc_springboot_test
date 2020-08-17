/**
 * 
 */
package com.mc.springboot.rest.error;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author praveen
 *
 */
@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(CityNotFoundException.class)
	public final ResponseEntity<CustomErrorDetails> handleCityNotFoundException(CityNotFoundException ex, WebRequest request) {
		CustomErrorDetails errorDetails = new CustomErrorDetails(ex.getErrorStatus() != null ? ex.getErrorStatus().value():400, new Date(), ex.getMessage());
		return new ResponseEntity<>(errorDetails, ex.getErrorStatus() != null ? ex.getErrorStatus() : HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<CustomErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		CustomErrorDetails errorDetails = new CustomErrorDetails(500, new Date(), ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}

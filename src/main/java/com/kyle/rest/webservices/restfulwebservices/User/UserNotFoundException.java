package com.kyle.rest.webservices.restfulwebservices.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial") // Need to investigate why it is needed?
//Need a More specific 404 data/user not found. ONly needed if @ExceptionHandler not used
//@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException { // Default 500 internal server error

	public UserNotFoundException(String message) {
		super(message);
		
	}

}

package com.kyle.rest.webservices.restfulwebservices.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial") // Need to investigate
@ResponseStatus(code = HttpStatus.NOT_FOUND)//Need a More specific 400 data/user not found
public class UserNotFoundException extends RuntimeException { // Default 500 internal server error

	public UserNotFoundException(String message) {
		super(message);
		
	}

}

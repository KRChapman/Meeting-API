package com.kyle.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kyle.rest.webservices.restfulwebservices.User.UserNotFoundException;

// CUSTOM ERROR HANDLING CLASS
// video 142 step 12 REST
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));// message from UserNotFoundException
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	
	// Validation error handling
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(// Comes from @Valid check in resource/controller
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // Potential for many validation errors so need custom message

//		 messsage+=	 "Total Errors:" + ex.getErrorCount() + " First Error:" + ex.getFieldError().getDefaultMessage();

		StringBuilder defualtmessage = new StringBuilder();
		ex.getAllErrors().forEach(ele->{
		defualtmessage.append("Error:").append( ele.getDefaultMessage()).append(" ").toString();
		});
		

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				defualtmessage.toString(), request.getDescription(false));// message from UserNotFoundException
		
		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
		

	}

}

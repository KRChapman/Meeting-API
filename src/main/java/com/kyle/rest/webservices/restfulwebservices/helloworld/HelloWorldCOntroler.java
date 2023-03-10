package com.kyle.rest.webservices.restfulwebservices.helloworld;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldCOntroler {
	
	@GetMapping(path = "/")
	public String helloWorld() {
		return "Hello World"; 
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World"); 
	}
	
	@RequestMapping(method = RequestMethod.GET ,path="/courses")
	public String retriveAllCourss(){ // returns data because of  @ResponseBody
		return "Hello RequestMethod.GET"; 
	}

}

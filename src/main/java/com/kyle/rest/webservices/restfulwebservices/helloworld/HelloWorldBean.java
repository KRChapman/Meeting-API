package com.kyle.rest.webservices.restfulwebservices.helloworld;



// No autowiring into other classes since not a component just a plain bean/pjo
// must be initialized manualy
public class HelloWorldBean {// POJO for now until defined as a bean

	private String message;

	public HelloWorldBean(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
}

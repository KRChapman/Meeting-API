package com.kyle.rest.webservices.restfulwebservices;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//import com.kylelearn.learnspringframework.examples.a1Dependency.DepInjectionLauncherApplication;
//import com.kylelearn.learnspringframework.examples.a1Dependency.YourBusinessClass;

@SpringBootApplication
//@Configuration
//@ComponentScan("com.kyle.rest.webservices.restfulwebservices.helloworld") // will look in current package
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
		
//		try (var context = new AnnotationConfigApplicationContext(RestfulWebServicesApplication.class)) {
//			
//			Arrays.stream(context.getBeanDefinitionNames())
//				.forEach(System.out::println); 
//
////	//		context.getBean(HelloWorldCOntroler.class);
//		} catch (BeansException e) {
//			e.printStackTrace();
//		}
	
	
	}

}

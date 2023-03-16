package com.kyle.rest.webservices.restfulwebservices.security;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		1) All requests should be authenticated (Customizable disable on certain routes?)
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
//		2) If a request is not authenticated, a web page is shown THE FORM DEFAULT
		http.httpBasic(withDefaults());// (Can Change to custom page?)
		
		
//		3) CSRF -> POST, PUT DO THIS FOR REST API
		http.csrf().disable();// FOR REST API
		return http.build(); //disables it for get request
	}
}

package com.kyle.rest.webservices.restfulwebservices.User;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	private UserDaoService service;

	public UserResource(UserDaoService service) {
		this.service = service;
	}

	// GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// GET /users
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
	User user = service.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id:"+id);
		
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
	
		
		
		User savedUser = service.save(user);
                      // takes current URL and ads id and replaces id with the savedUser id
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId()) //    http://localhost:8080/users/{id}
						.toUri();   
		// for returning 201 created instead of 200 success and sends a location url to consumer of API/resource
		return ResponseEntity.created(location).build();// http://localhost:8080/users/5
	}
}

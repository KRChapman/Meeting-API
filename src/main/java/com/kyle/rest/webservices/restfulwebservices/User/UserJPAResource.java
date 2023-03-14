package com.kyle.rest.webservices.restfulwebservices.User;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kyle.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJPAResource {
	
	private UserDaoService service;
	private UserRepository repository;
	
	public UserJPAResource(UserDaoService service, UserRepository repository) {
		this.service = service;
		this.repository = repository;
	}

	// GET /users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return repository.findAll();
	}

	// GET /users
	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id) {
	//User user = repository.findById(id).orElse(null);
	Optional<User> user = repository.findById(id);	
	
		if(user.isEmpty())  // user==null
			throw new UserNotFoundException("id:"+id);
		System.out.println(user);
		return user.get();
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
	
		
		
		User savedUser = repository.save(user);
		
		// FOR SENDING EXTRA INFORMATION ABOUT LOCATION TO FIND Data at a specific url ie: http://localhost:8080/users/2
                      // takes current URL and ads id and replaces id with the savedUser id
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId()) //    http://localhost:8080/users/{id}
						.toUri();   
		// for returning 201 created instead of 200 success and sends a location url to consumer of API/resource
		return ResponseEntity.created(location).build();// http://localhost:8080/users/5
	}
	
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
	}
}

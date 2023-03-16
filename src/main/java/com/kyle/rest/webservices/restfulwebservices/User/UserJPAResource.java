package com.kyle.rest.webservices.restfulwebservices.User;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kyle.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.kyle.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJPAResource {
	

	private UserRepository userRepository;
	private PostRepository postRepository;// Maybe best Practice to have a PostRsource/PostJPAResource class/component/bean
	
	public UserJPAResource( PostRepository postRepository, UserRepository repository) {

		this.userRepository = repository;
		this.postRepository = postRepository;
	}

	// GET /users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	// GET /users
	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id) {
	//User user = repository.findById(id).orElse(null);
	Optional<User> user = userRepository.findById(id);	
	
		if(user.isEmpty())  // user==null
			throw new UserNotFoundException("id:"+id);

		return user.get();
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
	
		
		
		User savedUser = userRepository.save(user);
		
		
		// SETS A URL ON THE LOCATION HEADER
		// FOR SENDING EXTRA INFORMATION ABOUT LOCATION TO FIND Data at a specific url ie: http://localhost:8080/users/2
                      // grabs current URL with the savedUser id
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId()) //    http://localhost:8080/users/{id}
						.toUri();   
		// for returning 201 created instead of 200 success and sends a location url to consumer of API/resource
		return ResponseEntity.created(location).build();// http://localhost:8080/users/5
	}
	
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getUserPosts(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);	
		
		if(user.isEmpty())  // user==null
			throw new UserNotFoundException("id:"+id);
	//	select p1_0.user_id,p1_0.id,p1_0.description from post p1_0 where p1_0.user_id=?
		// Select USER_ID, ID, DESCRIPTION From Post where USER_ID = 10002
		// USER is not returned since @JsonIgnore over User in the POST model 
		List<Post>  posts = user.get().getPosts();
		return posts;
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post)
	{
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		// SETS A URL ON THE LOCATION HEADER
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();   

		return ResponseEntity.created(location).build();

	}
	
	@GetMapping("/jpa/users/{id}/posts/{postID}")
	public ResponseEntity<?> getPost(@PathVariable int id, @PathVariable int postID) //throws Exception 
	{
		Optional<Post> post = postRepository.findById(postID);	

		// Not needed with EmptyJsonResponse() since already serialized with json
		  HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json");    

		if(post.isEmpty())  
			return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK); 
		//return new ResponseEntity<>(headers, HttpStatus.OK); // NO BODY no {} but ResponseEntity<Post>  ok
		//	return new EmptyJsonResponse();


		 return new ResponseEntity<Post>(post.get(), headers, HttpStatus.OK);
	}
}

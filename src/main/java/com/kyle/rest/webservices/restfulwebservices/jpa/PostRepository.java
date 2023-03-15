package com.kyle.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyle.rest.webservices.restfulwebservices.User.Post;




public interface PostRepository extends JpaRepository<Post, Integer> {

}

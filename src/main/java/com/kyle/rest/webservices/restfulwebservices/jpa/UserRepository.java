package com.kyle.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyle.rest.webservices.restfulwebservices.User.User;



public interface UserRepository extends JpaRepository<User, Integer> {

}

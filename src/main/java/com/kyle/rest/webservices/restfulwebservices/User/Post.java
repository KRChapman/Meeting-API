package com.kyle.rest.webservices.restfulwebservices.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

//create sequence post_seq start with 1 increment by 50
//
//create table post (
//id integer not null, 
//description varchar(255), 
//user_id integer, 
//primary key (id)
//);
//
//alter table post 
//add constraint post_to_user_foreign_key
//foreign key (user_id) references user;


@Entity
public class Post {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String description;
	// fetch = FetchType.LAZY
	@ManyToOne(fetch = FetchType.LAZY)	// Eager would bring User along with posts
	@JsonIgnore
	private User user;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	// ENTIRE USER OBJECT IS MODELED FOR THIS PROPERTY
	// OPTIONAL SINCE NOT NEEDED
	// @JsonIgnore makes this not return with the Response
	public User getUser() {
		return user;
	//	return user.getId();
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

}

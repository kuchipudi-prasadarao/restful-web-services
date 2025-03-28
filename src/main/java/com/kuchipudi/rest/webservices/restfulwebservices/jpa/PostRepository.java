package com.kuchipudi.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuchipudi.rest.webservices.restfulwebservices.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	// This interface will automatically be implemented by Spring Data JPA
	// No need to write any code here
	// It provides methods like save(), findById(), findAll(), deleteById() etc.

}

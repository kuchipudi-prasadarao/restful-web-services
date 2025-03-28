/**
 * 
 */
package com.kuchipudi.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kuchipudi.rest.webservices.restfulwebservices.dao.UserDaoService;
import com.kuchipudi.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.kuchipudi.rest.webservices.restfulwebservices.model.User;

import jakarta.validation.Valid;

/**
 * 
 */
@RestController
public class UserController {
	
	@Autowired
	private UserDaoService userDaoService;

	@GetMapping(path = "/users")
	public List<User> retriveAllUsers() {
		return userDaoService.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retriveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id:" + id);
		}
		EntityModel<User> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		entityModel.add(linkTo.withRel("all-users"));
		return entityModel;
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userDaoService.deleteById(id);
		
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody	 User user) {
		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}

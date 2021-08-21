package com.task.webapp.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.task.webapp.hibernate.UserApp;
import com.task.webapp.hibernate.model.User;

@RestController
public class UserController {
	
	UserApp app = new UserApp();
	
	/*POST A USER*/
	@CrossOrigin
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody User user)
	{
	   app.postUser(user.getUserName(), user.getEmail(), user.getPassword(), user.getRole());
	   return new ResponseEntity<>("User is created successfully", HttpStatus.CREATED);
	}
	
	/*GET BY ID*/
	@CrossOrigin
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getById(@PathVariable("id") int id)
	{
		return new ResponseEntity<>(app.getUserById(id), HttpStatus.OK);
	}
	
	/*DELETE A USER*/
	@CrossOrigin
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") int id) 
	{ 
	    app.deleteUser(id);
	    return new ResponseEntity<>("User is deleted successsfully", HttpStatus.OK);
	}
}

package com.task.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.webapp.authentication.JWTUtility;
import com.task.webapp.authentication.UserService;
import com.task.webapp.authentication.model.JwtRequest;
import com.task.webapp.authentication.model.JwtResponse;

@RestController
public class AuthController {
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/login")
	public String auth() {
		return "welcome to auth";
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception
	
	{
        //auth part
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							jwtRequest.getUserName(),
							jwtRequest.getPassword()
					)
			);	
		} catch (BadCredentialsException e){
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		//creating jwt
		final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUserName());
		final String token = jwtUtility.generateToken(userDetails);
		
		return new JwtResponse(token);
		
	} 
		
	
}

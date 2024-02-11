package com.instagram.myClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.myClone.exceptions.UserException;
import com.instagram.myClone.modal.User;
import com.instagram.myClone.service.UserService;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<User> registerUserHandler(@RequestBody User user) throws UserException {
		
		User createdUser = userService.registerUser(user);
		
		return new ResponseEntity<User>(createdUser, HttpStatus.OK);
	}
}

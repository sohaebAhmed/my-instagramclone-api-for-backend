package com.instagram.myClone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.myClone.exceptions.UserException;
import com.instagram.myClone.modal.User;
import com.instagram.myClone.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("id/{id}")
	public ResponseEntity<User> findUserByHandler(@PathVariable Integer id) throws UserException {
		
		User user = userService.findUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}

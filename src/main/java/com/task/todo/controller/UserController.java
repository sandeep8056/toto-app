package com.task.todo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.todo.dto.UserRequestDto;
import com.task.todo.entities.User;
import com.task.todo.serviceImpl.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/users")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequestDto userRequest) {
		String message = userServiceImpl.saveUser(userRequest);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('DeleteUser')")
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {
		return new ResponseEntity<>(userServiceImpl.deleteUser(userId), HttpStatus.OK);
	}

}

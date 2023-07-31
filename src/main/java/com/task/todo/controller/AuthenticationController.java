package com.task.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.todo.dto.JwtAuthRequest;
import com.task.todo.dto.JwtAuthResponseDto;
import com.task.todo.serviceImpl.CustomUserDetailsService;
import com.task.todo.utils.JwtUtils;

@RestController
public class AuthenticationController {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/generateToken")
	public ResponseEntity<JwtAuthResponseDto> createToken(@RequestBody JwtAuthRequest authRequest) {
		
		System.out.println(authRequest.getEmail());

		this.authenticate(authRequest.getEmail(), authRequest.getPassword());

		UserDetails details = this.customUserDetailsService.loadUserByUsername(authRequest.getEmail());

		String token = this.jwtUtils.generateToken(details);

		JwtAuthResponseDto authResponseDto = new JwtAuthResponseDto();

		authResponseDto.setToken(token);

		return new ResponseEntity<>(authResponseDto, HttpStatus.OK);

	}

	private void authenticate(String email, String password) {

		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

	}
}

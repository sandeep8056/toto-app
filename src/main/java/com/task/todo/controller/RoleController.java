package com.task.todo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.todo.service.RoleService;

@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	
	@PostMapping("/roles")
	public ResponseEntity<?>  saveRole(@RequestParam("roleName") String roleName){
		return new ResponseEntity<>(roleService.addRole(roleName),HttpStatus.OK);
	}
	
	

}

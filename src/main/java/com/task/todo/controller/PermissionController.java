package com.task.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.todo.serviceImpl.PermissionServiceImpl;

@RestController
public class PermissionController {

	@Autowired
	private PermissionServiceImpl permissionService;

	@PreAuthorize("hasRole('AddPermission')")
	@PostMapping("/permissions")
	public ResponseEntity<?> savePermission(@RequestParam("permissionAction") String permissionAction) {
		return new ResponseEntity<>(permissionService.addPermission(permissionAction), HttpStatus.CREATED);
	}

}

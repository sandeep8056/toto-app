package com.task.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.todo.dto.RolePermissionAssignDto;
import com.task.todo.serviceImpl.RolePermissionServiceImpl;

@RestController
public class RolePermissionController {

	@Autowired
	private RolePermissionServiceImpl permissionServiceImpl;

	
	@PreAuthorize("hasRole('AssignPermission')")
	@PostMapping("role/{roleId}/permission")
	public ResponseEntity<?> assignPermissionToRole(@PathVariable("roleId") Integer roleId,
			@RequestBody RolePermissionAssignDto assignDto) {
		try {
			return new ResponseEntity<>(permissionServiceImpl.assignPermissionToRole(roleId, assignDto), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}

package com.task.todo.service;

import com.task.todo.dto.RolePermissionAssignDto;

public interface IRolePermission {
	
	public String assignPermissionToRole(Integer roleId, RolePermissionAssignDto assignDto);

}

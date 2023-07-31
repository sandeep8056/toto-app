package com.task.todo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.todo.dto.RolePermissionAssignDto;
import com.task.todo.entities.Permission;
import com.task.todo.entities.Role;
import com.task.todo.entities.RolePermission;
import com.task.todo.entities.RolePermissionId;
import com.task.todo.exception.PermissionNotFoundException;
import com.task.todo.exception.RoleNotFoundException;
import com.task.todo.repository.IPermissionRepository;
import com.task.todo.repository.IRolePermissionRepository;
import com.task.todo.repository.IRoleRepository;
import com.task.todo.service.IRolePermission;

@Service
public class RolePermissionServiceImpl implements IRolePermission {

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private IPermissionRepository permissionRepository;

	@Autowired
	private IRolePermissionRepository rolePermissionRepository;

	@Override
	public String assignPermissionToRole(Integer roleId, RolePermissionAssignDto assignDto) {

		try {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new RoleNotFoundException("Role not found  by given id"));

		List<Permission> permissionList = permissionRepository.findAllById(assignDto.permissionId);

		List<Integer> foundPermissionId = permissionList.stream().map(permission -> permission.getPermissionId())
				.collect(Collectors.toList());

		List<Integer> permissionIdFromDto = assignDto.getPermissionId();

		List<Integer> notFoundPermissoinIds = permissionIdFromDto.stream().filter(id -> !foundPermissionId.contains(id))
				.collect(Collectors.toList());

		if (!notFoundPermissoinIds.isEmpty()) {
			throw new PermissionNotFoundException("Permission not found for Ids " + notFoundPermissoinIds);
		}

		List<RolePermission> rolePermissions = new ArrayList<>();
		for (Permission permission : permissionList) {

			Integer rolesId = role.getRoleId();
			Integer permissionId = permission.getPermissionId();

			RolePermissionId rolePermissionId = new RolePermissionId(rolesId, permissionId);

			RolePermission rolePermission = new RolePermission();

			rolePermission.setRolePermissionId(rolePermissionId);
			rolePermission.setPermissions(permission);
			rolePermission.setRoles(role);

			rolePermissions.add(rolePermission);
		}

		rolePermissionRepository.saveAll(rolePermissions);

		return "permissions assigned successfully";
		}catch (Exception e) {
			throw e;
		}
	}

}

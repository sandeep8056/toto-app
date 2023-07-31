package com.task.todo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.todo.entities.Role;
import com.task.todo.repository.IRoleRepository;
import com.task.todo.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private IRoleRepository roleRepository;

	public String addRole(String roleName) {
		
		try {
		Role role = new Role();
		role.setRoleName(roleName);
		roleRepository.save(role);
		return "Role added successfully";
		}catch (Exception e) {
			throw e;
		}
		
	}
	
	

}

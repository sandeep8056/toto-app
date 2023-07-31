package com.task.todo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.todo.entities.Permission;
import com.task.todo.repository.IPermissionRepository;
import com.task.todo.service.IPermissionService;

@Service
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private IPermissionRepository permissionRepository;
	
	@Override
	public String addPermission(String permissionAction) {
		
		try {
		Permission permission = new Permission();
		permission.setPermissionAction(permissionAction);
		
		var permissions = permissionRepository.save(permission);
		
		if(permissions != null) {
		return "Permission save successfully";
		}else {
			return "Permission not saved";
		}
		
		}catch (Exception e) {
			throw e;
		}
		
		
		
	}

}

package com.task.todo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.todo.entities.Role;
import com.task.todo.entities.User;
import com.task.todo.entities.UserRole;
import com.task.todo.entities.UserRoleId;
import com.task.todo.exception.RoleNotFoundException;
import com.task.todo.exception.UserNotFoundException;
import com.task.todo.repository.IRoleRepository;
import com.task.todo.repository.IUserRepository;
import com.task.todo.repository.IUserRoleRepository;
import com.task.todo.service.IUserRoleService;

import com.task.todo.utils.ErrorMessageConstant;


@Service
public class UserRoleServiceImpl  implements IUserRoleService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private IUserRoleRepository userRoleRepository;
	

	@Override
	public String assignRoleToUser(Integer userId, Integer roleId) {
		
		
		try {
			
		User user=	userRepository.findById(userId)
			.orElseThrow(()-> new UserNotFoundException(ErrorMessageConstant.USER_NOT_FOUND));
			
		Role role = roleRepository.findById(roleId)
					.orElseThrow(() -> new RoleNotFoundException(ErrorMessageConstant.ROLE_NOT_FOUND));
		
		
		UserRoleId userRoleId = new UserRoleId();
		userRoleId.setUserId(userId);
		userRoleId.setRoleId(roleId);
		
		UserRole userRole = new UserRole();
		userRole.setRoles(role);
		userRole.setUser(user);
		userRole.setUserRoleId(userRoleId);
		
		userRoleRepository.save(userRole);
		
		
		
			
		}catch (Exception e) {
			
			throw e;
		}
		
		return "Role assign successfully to user ";
	}

}

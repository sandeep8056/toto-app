package com.task.todo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.task.todo.entities.User;
import com.task.todo.repository.IPermissionRepository;
import com.task.todo.repository.IUserRepository;
import com.task.todo.utils.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IPermissionRepository ipermission;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("user not found by given user email" + email));
		
		
		

		Integer	userId  = user.getUserId(); 
		List<String> permissionAction =  ipermission.findPeremissionActionByUserId(userId);
		

		return new CustomUserDetails(user,permissionAction);
	}

}

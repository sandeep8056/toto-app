package com.task.todo.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.task.todo.entities.User;
import com.task.todo.repository.IPermissionRepository;
import com.task.todo.repository.IRolePermissionRepository;
import com.task.todo.repository.IUserRepository;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	List<String> permission;
	

	
	public CustomUserDetails(User user) {
		this.user = user;
		
	}
	
	public CustomUserDetails(User user2, List<String> permissionAction) {
			this.user = user2;
			this.permission=permissionAction;
			
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
	
		
		List<GrantedAuthority> authority = new ArrayList<>();
		
		
		 permission.forEach( permission ->{
			authority.add(new SimpleGrantedAuthority("ROLE_"+permission));
		});
		return authority;
	}

	@Override
	public String getPassword() {
		
		return  user.getPassword() ;
	}

	@Override
	public String getUsername() {
		
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

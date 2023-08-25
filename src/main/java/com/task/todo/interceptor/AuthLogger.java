package com.task.todo.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.task.todo.entities.User;
import com.task.todo.exception.UserNotFoundException;
import com.task.todo.repository.IPermissionRepository;
import com.task.todo.repository.IUserRepository;
import com.task.todo.utils.JwtUtils;

@Component
public class AuthLogger implements HandlerInterceptor {
	
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IPermissionRepository permissionRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		String authHeader =  request.getHeader("Authorization");
		
		String tokenString = (authHeader != null ) ? authHeader.split(" ")[1]: null ;
		
		if(tokenString != null) {
			 	String email = jwtUtils.getUsernameFromToken(tokenString);
			 	
			 	User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("user not found"));
			 	
			 	List<String> permission = permissionRepository.findPeremissionActionByUserId(user.getUserId());

			 	
			 	request.setAttribute("logedInId", user.getUserId());
			 	request.setAttribute("permission", permission);
			 	
			 	
			 				 	
		}
		
		
		
		
		
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
	
}

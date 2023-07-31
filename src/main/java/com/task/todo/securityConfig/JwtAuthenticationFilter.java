package com.task.todo.securityConfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.task.todo.serviceImpl.CustomUserDetailsService;
import com.task.todo.utils.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter{

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String requestToken = request.getHeader("Authorization");
		
		String userName =null;
		String token = null;
		
		
		if(requestToken != null && requestToken.startsWith("Bearer")) {
			
			token =requestToken.substring(7);
			
			try {
				userName =  jwtUtils.getUsernameFromToken(token);
			
			}catch (IllegalArgumentException e) {
				throw e;
			}catch (ExpiredJwtException e) {
			throw e;	
			}
			
		
		
		//once token is get   validate the user
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			if(this.jwtUtils.validateToken(token, userDetails)) {
				
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
								new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				System.out.println("Invalid jwt Token");
			}
			
		}else {
			System.out.println("Username is null or context is not null");
		}
		}
		
		
		filterChain.doFilter(request, response);
	}

}

package com.task.todo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class ConfigInterceptor implements WebMvcConfigurer {

	@Autowired
	private AuthLogger authLogger;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
	registry.addInterceptor(authLogger);	
		
	}

		
	
	
	
}

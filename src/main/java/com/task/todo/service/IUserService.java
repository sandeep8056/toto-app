package com.task.todo.service;

import com.task.todo.dto.UserRequestDto;

public interface IUserService {
	public String saveUser(UserRequestDto  userRequest);	
	public String deleteUser(Integer userId);
}
	
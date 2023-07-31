package com.task.todo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.task.todo.dto.TaskRequestDto;
import com.task.todo.dto.UserRequestDto;
import com.task.todo.entities.Task;
import com.task.todo.entities.User;
import com.task.todo.entities.UserRole;

@Component
public class Mapper {
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public User UserRequestDtoToEntity(UserRequestDto userRequest) {
		
		User  user = new User();
		
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setEmail(userRequest.getEmail());
		
		user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		
		
		
		
		return user;
	}
	
	
	public UserRequestDto  EntityToUserRequestDto( User user) {

		UserRequestDto userDto = new UserRequestDto();

		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());

		return userDto;
	}
	
	
	public TaskRequestDto TaskEntityToTaskRequestDto(Task task) {
		
		TaskRequestDto taskReq = new TaskRequestDto();
		
		taskReq.setTitle(task.getTitle());
		taskReq.setDescription(task.getDescription());
		taskReq.setTaskId(task.getTaskId());
		
		return taskReq;
	}
	
	public Task TaskRequestDtoToTaskEntity(TaskRequestDto taskDto) {
		
		Task task = new Task();
		task.setTaskId(taskDto.getTaskId());
		task.setTitle(taskDto.getTitle());
		task.setDescription(taskDto.getDescription());
		
		return task;
	}

}

package com.task.todo.service;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import com.task.todo.dto.PageRequestDto;
import com.task.todo.dto.TaskRequestDto;
import com.task.todo.entities.Task;

public interface ITaskService {
	
	public String createTask(TaskRequestDto taskDto);
	
	public String deleteTask(Integer taskId);
	
	public String updateTask(Integer taskId, TaskRequestDto taskDto);
	
	public Page<Task> getAllTask(@RequestBody PageRequestDto dt0);
	
	
	
}

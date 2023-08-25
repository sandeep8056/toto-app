package com.task.todo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.task.todo.dto.IUserTaskResponseDto;
import com.task.todo.dto.UserTaskAssignDto;

public interface IUserTaskService {
	
	public String assignTaskToUser(@RequestBody UserTaskAssignDto userTaskDto);
	
	
	public String updateStatus(@PathVariable Integer userId, @PathVariable Integer taskId);
	
	
	public Page<IUserTaskResponseDto> getUserTaskByUserId(@RequestParam Integer userId, Integer page, Integer size,Integer logedId,List<String> permission);
	
	
	
	

}

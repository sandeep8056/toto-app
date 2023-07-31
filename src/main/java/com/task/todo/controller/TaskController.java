package com.task.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.todo.dto.PageRequestDto;
import com.task.todo.dto.TaskRequestDto;
import com.task.todo.service.ITaskService;

@RestController
public class TaskController {

	@Autowired
	private ITaskService taskService;
	
	@PreAuthorize("hasRole('ViewAllTask')")
	@GetMapping("/tasks")
	public ResponseEntity<?>  getAllTask(@RequestBody PageRequestDto pageRequest){
		return new ResponseEntity<>(taskService.getAllTask(pageRequest),HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('CreateTask')")
	@PostMapping("/tasks")
	public ResponseEntity<?> createTask(@RequestBody TaskRequestDto taskRequest){
		return new ResponseEntity<>(taskService.createTask(taskRequest),HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('UpdateTask')")
	@PutMapping("/tasks/{taskId}")
	public ResponseEntity<?> updateTask(@PathVariable("taskId") Integer taskId ,@RequestBody TaskRequestDto taskRequest){
		return new ResponseEntity<>(taskService.updateTask(taskId, taskRequest),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('DeleteTask')")
	@DeleteMapping("/task/{taskId}")
	public ResponseEntity<?> deleteTask(@PathVariable("taskId")Integer taskId){
		return new ResponseEntity<>(taskService.deleteTask(taskId),HttpStatus.OK);
	}
	
	
}

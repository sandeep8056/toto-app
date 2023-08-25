package com.task.todo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.todo.dto.UserTaskAssignDto;
import com.task.todo.serviceImpl.UserTaskServiceImpl;

@RestController
public class UserTaskController {

	@Autowired
	private UserTaskServiceImpl userTaskServiceImpl;

	@PreAuthorize("hasRole('AssignTask')")
	@PostMapping("/users/tasks")
	public ResponseEntity<?> assignTask(@RequestBody UserTaskAssignDto userTaskDto) {
		return new ResponseEntity<>(userTaskServiceImpl.assignTaskToUser(userTaskDto), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('UpdateStatus')")
	@PutMapping("/users/{userId}/tasks/{taskId}")
	public ResponseEntity<?> updateStatus(@PathVariable("userId") Integer userId,
			@PathVariable("taskId") Integer taskId) {
		return new ResponseEntity<>(userTaskServiceImpl.updateStatus(userId, taskId), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('GetUserTask')")
	@GetMapping("/users/{userId}/tasks")
	public ResponseEntity<?> getTaskByUserId(@PathVariable("userId") Integer userId,
			@RequestParam(defaultValue = "0", name = "page") Integer page,
			@RequestParam(defaultValue = "10", name = "size") Integer size,
			@RequestAttribute(name = "logedInId") int logedId,
			@RequestAttribute(name = "permission") List<String> permissions
			) {
		return new ResponseEntity<>(userTaskServiceImpl.getUserTaskByUserId(userId, page, size,logedId,permissions), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('UserTaskFilter')")
	@GetMapping("user/{userId}/task-filter")
	public ResponseEntity<?> getTaskAsPerFilter(@PathVariable("userId") Integer userId,
			@RequestParam("status") String status, @RequestParam("startDate") Date startDate,
			@RequestParam("lastDate") Date lastDate) {

		return new ResponseEntity<>(userTaskServiceImpl.getTaskAsPerFilter(userId, status, startDate, lastDate),
				HttpStatus.OK);
	}

}

package com.task.todo.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTaskAssignDto {
	
	@NotEmpty(message = "userId field cannot be empty or null")
	private Integer userId;
	
	@NotEmpty(message = "taskId field cannot be empty")
	@NotNull(message ="taskId field cannot be null")
	private List<Integer> taskId;
	
	@NotEmpty(message="due date field cannot be  empty")
	private Date dueDate;
	
	
	

}

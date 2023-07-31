package com.task.todo.dto;

import java.util.Date;

import com.task.todo.enums.Status;

public interface IUserTaskResponseDto {
	

	public String getFirstName();

	public String getLastName();

	public String getTitle();

	public String getDescription();

	public Date getAssignedDate();

	public Status getStatus();

	public Date getDueDate();
	
	public Date LastModifiedDate();

}

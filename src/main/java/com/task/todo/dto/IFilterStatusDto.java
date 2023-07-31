package com.task.todo.dto;

import java.util.Date;

public interface IFilterStatusDto {
	
	
	public String getTitle();
	
	public String getDescription();
	
	
	public String getStatus();
	
	public Date getAssignedDate();
	
	public Date getDueDate();

}

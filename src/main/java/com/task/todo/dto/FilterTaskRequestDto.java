package com.task.todo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterTaskRequestDto { 
	
	private String status;
	
	private Date startDate;
	
	private Date endDaste;
	

}

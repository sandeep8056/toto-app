package com.task.todo.utils;

import org.springframework.data.domain.Sort;

public class ConstantMessages {

	public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";

	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	public static final Integer PAGE_SIZE = 10;

	public static final Integer PAGE_NUMBER = 0;

	public static final Sort.Direction PAGE_DIRECTION = Sort.Direction.ASC;

	public static final Integer JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	// task

	public static final String TASK_UPDATED_SUCCESSFULLY = "task updated successfully ";

}

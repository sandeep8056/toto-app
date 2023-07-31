package com.task.todo.exception;

public class UserTaskNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserTaskNotFoundException(String message) {
		super(message);
	}

	public UserTaskNotFoundException() {

	}

}

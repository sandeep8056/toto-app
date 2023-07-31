package com.task.todo.exception;

public class PermissionNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public PermissionNotFoundException(String message) {
		super(message);
	}
	
	public PermissionNotFoundException() {
		
	}
}

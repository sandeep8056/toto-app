package com.task.todo.exception;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.autoconfigure.data.AbstractRepositoryConfigurationSourceSupport;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.val;


@RestControllerAdvice
public class GlobalExceptionControlAdvice {

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {

		Map<String, String> body = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			body.put(error.getField(), error.getDefaultMessage());
		});

		return new ResponseEntity<Map<String, String>>(body, HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(value= {UserNotFoundException.class})
	public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(value= {DataIntegrityViolationException.class})
	public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
		
		return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value= {TaskNotFoundException.class})
	public ResponseEntity<?> handleTaskNotFoundException(TaskNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= {UserTaskNotFoundException.class})
	public ResponseEntity<?> handleUserTaskNotFoundException(UserTaskNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(value= {RoleNotFoundException.class})
	public ResponseEntity<?> handleRoleNotFoundException(RoleNotFoundException ex){
		return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {PermissionNotFoundException.class})
	public ResponseEntity<?> handlePermissionNotFoundExceptio(PermissionNotFoundException ex){
		return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
}

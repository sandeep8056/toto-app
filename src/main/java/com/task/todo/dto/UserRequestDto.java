package com.task.todo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
	
	
	@NotBlank(message="firstName field of user cannot be blank or Empty")
	@Size(min = 2,max = 32,message="firstName must be between  2 and 32 characters long")
	private String firstName;
	
	@NotBlank(message="lastName field of user cannot be blank or Empty")
	@Size(min = 2,max = 32,message="lastName must be between  2 and 32 characters long")
	private String lastName;
	
	@Email(message="Please enter a valid email address")
	@NotNull(message = "email field cannot be null")
	private String email;
	
	@NotEmpty(message="password field cannot be empty")
	private String password;
	


}

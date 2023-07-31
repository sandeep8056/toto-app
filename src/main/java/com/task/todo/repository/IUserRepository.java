package com.task.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.todo.entities.User;

@Repository
public interface IUserRepository  extends JpaRepository<User,Integer>{

	Optional<User> findByEmail(String email);
	
	
	
	

}

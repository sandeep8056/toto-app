package com.task.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.todo.entities.Role;

public interface IRoleRepository  extends JpaRepository<Role,Integer>{

}

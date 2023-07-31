package com.task.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.todo.entities.UserRole;
import com.task.todo.entities.UserRoleId;

public interface IUserRoleRepository  extends JpaRepository<UserRole, UserRoleId> {

}

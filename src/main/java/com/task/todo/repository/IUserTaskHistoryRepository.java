package com.task.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.todo.entities.UserTaskHistory;

public interface IUserTaskHistoryRepository  extends JpaRepository<UserTaskHistory, Integer>{

}

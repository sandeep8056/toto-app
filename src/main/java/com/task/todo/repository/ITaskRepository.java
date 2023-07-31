package com.task.todo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.task.todo.entities.Task;

public interface ITaskRepository  extends JpaRepository<Task, Integer>{
	
	@Query(value = "SELECT * FROM tasks WHERE task_id IN :taskIds", nativeQuery = true)
    List<Task> findTasksByIds(@Param("taskIds") List<Integer> taskIds);
	
	
}

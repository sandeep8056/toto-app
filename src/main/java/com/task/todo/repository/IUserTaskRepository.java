package com.task.todo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.task.todo.dto.IUserTaskResponseDto;
import com.task.todo.entities.UserTask;
import com.task.todo.entities.UserTaskId;

public interface IUserTaskRepository extends JpaRepository<UserTask,UserTaskId> {

	List<UserTask> findByUserUserId(Integer userId);
	
	
	//@Query(value="select u.user_id,u.task_id, u.start_date, u.due_date, u.last_modified_date, u.status from user_tasks u where u.user_id = :userId and u.task_id = :taskId")
	
	
	//Optional<UserTask> findbyUserUserIdAndTaskTaskId(@Param("userId") Integer userId ,@Param("taskId") Integer taskId);

	
	
	/*
	@Query(	value="select u.first_name +"+" "+ "u.last_name as 'FullName' , Date(ut.start_date) as 'StartDate' , Date(ut.due_date) as 'EndDate', "
			+ "ut.status as 'Status' , Date(ut.last_modified_date) as 'LastUpdatedDate' , t.title as 'Title' , t.description as 'Description' "
			+ "from user u "
			+ "join user_tasks ut"
			+ "using (user_id)"
			+ "join tasks t \r\n"
			+ "using (task_id)\r\n"
			+ "where u.user_id = :userId;" , nativeQuery  = true)
			
			*/
	
	@Query(value="\r\n"
			+ "select  u.first_name  as 'FirstName',u.last_name as 'LastName',Date(ut.start_date) as 'AssignedDate',Date(ut.due_date) as 'DueDate',\r\n"
			+ "ut.status as 'Status',Date(ut.last_modified_date) as 'LastUpdatedAt',t.title as 'Title',t.description as 'Description' from users u \r\n"
			+ "join user_tasks ut \r\n"
			+ "using (user_id)\r\n"
			+ "join tasks t \r\n"
			+ "using (task_id)\r\n"
			+ "where u.user_id = :userId", nativeQuery = true)
	Page<IUserTaskResponseDto> getTaskByUserId(@Param("userId") Integer userId, Pageable pageable );
	

	
	

	
}

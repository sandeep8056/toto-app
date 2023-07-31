package com.task.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.task.todo.entities.Permission;

public interface IPermissionRepository  extends JpaRepository<Permission, Integer>{

	

	@Query(value = "select p.permission_action from users u "
			+ "join user_has_roles ur\r\n"
			+ "on u.user_id = ur.user_id\r\n"
			+ "join roles r\r\n"
			+ "on ur.role_id = r.role_id\r\n"
			+ "join role_has_permission rp \r\n" 
			+ "on r.role_id = rp.role_id\r\n"
			+ "join  permission p\r\n"
			+ "on rp.permission_id = p.permission_id\r\n"
			+ "where u.user_id = :userId", nativeQuery = true)
	List<String> findPeremissionActionByUserId(@Param("userId")  Integer userId);
}

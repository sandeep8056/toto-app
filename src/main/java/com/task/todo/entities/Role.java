package com.task.todo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	
	@Column(name="role_name")
	private String roleName;
	
	@OneToMany(mappedBy = "roles",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<UserRole> userRoles;
	
	
	@OneToMany(mappedBy = "roles",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<RolePermission> rolePermission;	
	

}

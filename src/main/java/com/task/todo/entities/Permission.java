package com.task.todo.entities;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( name="permission")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer permissionId;
	
	@Column(name = "permission_action")
	private String permissionAction;
	
	
	@OneToMany(mappedBy ="permissions",cascade = CascadeType.ALL )
	private List<RolePermission> rolePermission;
	
	
}

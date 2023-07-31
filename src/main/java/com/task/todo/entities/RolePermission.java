package com.task.todo.entities;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_has_permission")
public class RolePermission {

	@EmbeddedId
	private RolePermissionId rolePermissionId = new RolePermissionId();

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("roleId")
	@JoinColumn(name = "role_id")
	private Role roles;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("permissionId")
	@JoinColumn(name = "permission_id")
	private Permission permissions;

	@CreationTimestamp
	private Date createdAt;

}

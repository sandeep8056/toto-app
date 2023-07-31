package com.task.todo.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;


@Embeddable
public class RolePermissionId  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer roleId;
	
	private Integer permissionId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public RolePermissionId(Integer roleId, Integer permissionId) {
		super();
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public RolePermissionId() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(permissionId, roleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePermissionId other = (RolePermissionId) obj;
		return Objects.equals(permissionId, other.permissionId) && Objects.equals(roleId, other.roleId);
	}

	

	
}

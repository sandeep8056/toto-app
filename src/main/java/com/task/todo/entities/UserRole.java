package com.task.todo.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_has_roles")
public class UserRole {
	
	
	@EmbeddedId
	private UserRoleId userRoleId = new UserRoleId();
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST)
	@MapsId("userId")
	@JoinColumn(name="user_id")
	private User user;
	
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	@MapsId("roleId")
	private Role roles;
	
	
	@CreationTimestamp
	@Column(name="created_at")
	private Date createdAt;
	
	
	

	
	
}

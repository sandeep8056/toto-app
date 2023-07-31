package com.task.todo.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task.todo.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_tasks")
public class UserTask {

	@EmbeddedId
	private UserTaskId userTaskId = new UserTaskId();

	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	
	@ManyToOne
	@MapsId("taskId")
	@JoinColumn(name = "task_id")
	private Task task;

	@CreationTimestamp
	@Column(name = "start_date")
	private Date assignedDate;

	
	@Column(name = "due_date")
	private Date dueDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 100)
	private Status status;

	@Column(name = "last_modified_date")
	@UpdateTimestamp
	private Date lastModifiedDate;
	
	@OneToMany(mappedBy = "userTask", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	List<UserTaskHistory> userTaskHistory;

}

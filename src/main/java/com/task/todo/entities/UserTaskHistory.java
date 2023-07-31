package com.task.todo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.task.todo.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_task_history")
public class UserTaskHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userTaskHistoryId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status",nullable = false, length =50)
	private Status status;
	
	@UpdateTimestamp
	@DateTimeFormat
	private Date updatedAt;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(
			{
				@JoinColumn(name="task_id"),
				@JoinColumn(name="user_id")
	
			})
			private UserTask userTask;
	
	
	
	
	
}

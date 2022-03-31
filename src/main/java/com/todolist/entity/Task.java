package com.todolist.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "Task")
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
	private Integer id;

	@Column(length = 300, nullable = false)
	private String title;

	@Column(length = 300, nullable = false)
	private String description;

	@Column(columnDefinition = "DATE")
	private Date dueDate;
	
	@UpdateTimestamp
	@Column(columnDefinition="DATETIME(3)", nullable = false)
	private Date modifiedOn;

	@Column(columnDefinition = "DATETIME(3)", nullable = false, updatable = false)
	@CreationTimestamp
	private Date createdOn;

	
	@ManyToOne 
	@JoinColumn(name = "todoListId") 
	private TodoList todoList;
	 	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stateId", nullable = true)
	private State state;
}

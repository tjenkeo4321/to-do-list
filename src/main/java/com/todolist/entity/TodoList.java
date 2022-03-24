package com.todolist.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "TodoList")
public class TodoList implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
	private Integer id;

	@Column(length = 300, nullable = false)
	private String name;

	@Column(length = 300)
	private String description;
	
	@UpdateTimestamp
	@Column(columnDefinition="DATETIME(3)", nullable = false)
	private Date modifiedOn;

	@Column(columnDefinition = "DATETIME(3)", nullable = false, updatable = false)
	@CreationTimestamp
	private Date createdOn;
	
	/*
	 * @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "itemId", nullable = true) private List<Task> tasks;
	 */
}

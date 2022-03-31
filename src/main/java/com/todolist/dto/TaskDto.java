package com.todolist.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class TaskDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String title;

	private String description;

	private int stateId;
	
	private String state;

	private int todoListId;

	private Date dueDate;

}

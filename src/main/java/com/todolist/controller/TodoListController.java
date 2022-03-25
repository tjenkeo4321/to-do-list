package com.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.dto.StateDto;
import com.todolist.dto.TaskDto;
import com.todolist.dto.TodoListDto;
import com.todolist.entity.State;
import com.todolist.entity.Task;
import com.todolist.service.TodoListService;

@RestController
@RequestMapping("/todoList")
public class TodoListController {

	@Autowired
	TodoListService todoListService;

	@PostMapping("/state")
	public ResponseEntity<StateDto> addStatus(@RequestBody StateDto stateDto) {

		StateDto savedState = todoListService.addStatus(stateDto);

		return ResponseEntity.ok(savedState);
	}

	@PostMapping("/add")
	public ResponseEntity<TodoListDto> createTodolist(@RequestBody TodoListDto todoListDto) {

		TodoListDto savedTodoList = todoListService.createTodolist(todoListDto);

		return ResponseEntity.ok(savedTodoList);
		
		
	}
	
	@PostMapping("/addTaskTotoddolist")
	public ResponseEntity<TaskDto> addTaskTotodoList(@RequestBody TaskDto taskDto) {
		Task savedTask = todoListService.addNewTask(taskDto);
		taskDto.setId(savedTask.getId());
		return ResponseEntity.ok(taskDto);
		
	}
	
	@GetMapping("/getAllTodoLists")
	public ResponseEntity<List<TodoListDto>> addTaskTotodoList() {
		List<TodoListDto> todoListDtos = todoListService.getAllToDoLists();
		return ResponseEntity.ok(todoListDtos);
		
	}
	
	@GetMapping("/getAllTasks")
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> tasks = todoListService.getAllTasks();
		return ResponseEntity.ok(tasks);
		
	}
	
	@GetMapping("/getAllStates")
	public ResponseEntity<List<State>> getAllStates() {
		List<State> states = todoListService.getAllStates();
		return ResponseEntity.ok(states);
		
	}
}

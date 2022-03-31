package com.todolist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.dto.ResponseDto;
import com.todolist.dto.StateDto;
import com.todolist.dto.TaskDto;
import com.todolist.dto.TodoListDto;
import com.todolist.entity.State;
import com.todolist.entity.Task;
import com.todolist.entity.TodoList;
import com.todolist.exception.DataNotFoundException;
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
	
	@RequestMapping(value = "/todoListId/{todoListId}", method = RequestMethod.PATCH)
	public ResponseEntity<ResponseDto> updateToDoList(@PathVariable int todoListId, @RequestBody TodoListDto todoListDto) {
		Optional<TodoList> existingTodoList = todoListService.getToDoListById(todoListId);
		ResponseDto responseDto = new ResponseDto();
		if(existingTodoList.isPresent()) {
			TodoList todoList = existingTodoList.get();
			todoList.setName(todoListDto.getName());	
			todoList.setDescription(todoListDto.getDescription());
			try {
				todoListService.updateTodolist(todoList);
				responseDto.setSuccess(true);
				responseDto.setMessage("Updated successfully");
			}catch(Exception ex) {
				responseDto.setSuccess(false);
				responseDto.setMessage("Failed update");
			}
		} else {
			throw new DataNotFoundException("Data not found");
		}
		return ResponseEntity.ok(responseDto);
	}

	@PostMapping("/addTaskTotoddolist")
	public ResponseEntity<TaskDto> addTaskTotodoList(@RequestBody TaskDto taskDto) {
		Task savedTask = todoListService.addNewTask(taskDto);
		taskDto.setId(savedTask.getId());
		return ResponseEntity.ok(taskDto);
	}

	@RequestMapping(value = "/taskId/{taskId}", method = RequestMethod.PATCH)
	public ResponseEntity<ResponseDto> updateTask(@PathVariable int taskId, @RequestBody TaskDto taskDto) {
		Optional<Task> savedTask = todoListService.getTaskById(taskId);
		ResponseDto responseDto = new ResponseDto();
		if(savedTask.isPresent()) {
			Task task = savedTask.get();
			task.setTitle(taskDto.getTitle());
			task.setDescription(taskDto.getDescription());
			todoListService.updateTask(task);
			responseDto.setMessage("Updated Successfully");
		} else {
			throw new DataNotFoundException("Data not found");
		}
		
		return ResponseEntity.ok(responseDto);     
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

	@RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDto> deleteToDoList(@PathVariable int id) {
		ResponseDto responseDeleteDto = new ResponseDto();
		try {
			Optional<TodoList> todoList = todoListService.getToDoListById(id);
			if (todoList.isPresent()) {
				todoListService.deleteToDoList(todoList.get());
				responseDeleteDto.setSuccess(true);
				responseDeleteDto.setMessage("Successfully Deleted");
			} else {
				responseDeleteDto.setSuccess(false);
				responseDeleteDto.setMessage("Record not found");
			}
		} catch (Exception ex) {
			responseDeleteDto.setSuccess(false);

		}
		return ResponseEntity.ok(responseDeleteDto);
	}
	
	@RequestMapping(value = "/deleteTaskById/{taskId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDto> deleteTask(@PathVariable int taskId) {
		ResponseDto responseDeleteDto = new ResponseDto();
		try {
			Optional<Task> task = todoListService.getTaskById(taskId);
			if (task.isPresent()) {
				todoListService.deleteTaskById(taskId);
				responseDeleteDto.setSuccess(true);
				responseDeleteDto.setMessage("Successfully Deleted");
			} else {
				responseDeleteDto.setSuccess(false);
				responseDeleteDto.setMessage("Record not found");
				throw new DataNotFoundException("Data not found");
			}
		} catch (Exception ex) {
			responseDeleteDto.setSuccess(false);

		}
		return ResponseEntity.ok(responseDeleteDto);
	}
}

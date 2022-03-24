package com.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.dto.StateDto;
import com.todolist.dto.TaskDto;
import com.todolist.dto.TodoListDto;
import com.todolist.entity.State;
import com.todolist.entity.Task;
import com.todolist.entity.TodoList;
import com.todolist.repository.StateRepository;
import com.todolist.repository.TaskRepository;
import com.todolist.repository.TodoListRepository;

@Service
public class TodoListService {

	@Autowired
	StateRepository stateRepository;

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TodoListRepository todoListRepository;

	/**
	 * Define new State value
	 * 
	 * @param stateDto
	 * @return
	 */
	public StateDto addStatus(StateDto stateDto) {

		State state = new State();
		state.setName(stateDto.getName());

		State savedState = stateRepository.save(state);

		StateDto savedStateDto = new StateDto();
		savedStateDto.setName(savedState.getName());

		return savedStateDto;
	}

	/**
	 * Define new Todo List
	 * 
	 * @param todoListDto
	 * @return
	 */
	public TodoListDto createTodolist(TodoListDto todoListDto) {
		TodoList todoList = new TodoList();
		todoList.setName(todoListDto.getName());
		todoList.setDescription(todoListDto.getDescription());

		TodoList savedTodoList = todoListRepository.save(todoList);

		TodoListDto newToDoListDto = new TodoListDto();
		newToDoListDto.setName(savedTodoList.getName());
		newToDoListDto.setDescription(savedTodoList.getDescription());

		return newToDoListDto;
	}

	/**
	 * @param taskDto
	 * @return
	 */
	public Task addNewTask(TaskDto taskDto) {
		Task task = new Task();
		task.setTitle(taskDto.getTitle());
		task.setDescription(taskDto.getDescription());
		task.setDueDate(taskDto.getDueDate());

		State state = stateRepository.getById(taskDto.getStateId());
		task.setState(state);

		TodoList todoList = todoListRepository.getById(taskDto.getTodoListId());
		task.setTodoList(todoList);

		Task savedTask = taskRepository.save(task);

		return savedTask;
	}

}

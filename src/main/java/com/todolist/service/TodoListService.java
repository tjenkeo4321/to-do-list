package com.todolist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		newToDoListDto.setId(savedTodoList.getId());
		newToDoListDto.setName(savedTodoList.getName());
		newToDoListDto.setDescription(savedTodoList.getDescription());

		return newToDoListDto;
	}
	
	public TodoList updateTodolist(TodoList todoList) {
		TodoList savedTodoList = todoListRepository.save(todoList);
		return savedTodoList;
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
	
	public List<TodoListDto> getAllToDoLists(){
		List<TodoList> listToDoList = todoListRepository.findAll();
		List<TodoListDto> todoListDtos = new ArrayList<TodoListDto>(); 
		TodoListDto todoListDto = null;
		for(TodoList todoList:listToDoList) {
			todoListDto = new TodoListDto();
			todoListDto.setId(todoList.getId());
			todoListDto.setName(todoList.getName());
			todoListDto.setDescription(todoList.getDescription());
			todoListDtos.add(todoListDto);
		}
	 return todoListDtos;	
	}
	
	public List<Task> getAllTasks(){
		return  taskRepository.findAll();
	}
	
	public Task updateTask(Task task) {
		Task updatedTask = taskRepository.save(task);
		return updatedTask;
	}
	
	public List<State> getAllStates(){
		return  stateRepository.findAll();
	}
	
	public TodoList updateToDoList(TodoList todoList) {
		TodoList savedTodoList = todoListRepository.getById(todoList.getId());
		savedTodoList.setDescription(todoList.getDescription());
		savedTodoList.setName(todoList.getName());
		return todoListRepository.save(savedTodoList);
	}
	
	public Optional<TodoList> getToDoListById(int id) {
		return todoListRepository.findById(id);
	}
	
	public Optional<Task> getTaskById(int id) {
		return taskRepository.findById(id);
	}
	
	public void deleteToDoList(TodoList todoList) {
		 todoListRepository.delete(todoList);
	}
	
	public void deleteTaskById(int id){
		taskRepository.deleteById(id);
	}
	
    	

}

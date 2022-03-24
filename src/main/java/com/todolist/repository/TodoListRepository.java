package com.todolist.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.entity.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Integer>{

}

package com.todolist.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.entity.State;

public interface StateRepository extends JpaRepository<State, Integer>{

}

package com.example.todoserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoserver.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, String>{
    
}

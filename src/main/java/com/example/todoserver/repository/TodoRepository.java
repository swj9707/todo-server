package com.example.todoserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.todoserver.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, String>{

    List<TodoEntity> findByUserId(String userId);

}

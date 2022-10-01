package com.example.todoserver.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.todoserver.entity.TodoEntity;

public class TodoRepositoryTest {
    
    @Autowired
    TodoRepository todoRepository;
    
    @Test
    void testFindByUserId() {

    }
}

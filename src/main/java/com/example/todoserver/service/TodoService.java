package com.example.todoserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoserver.entity.TodoEntity;
import com.example.todoserver.repository.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoService {
    
    @Autowired
    TodoRepository todoRepository;

    public void validate(final TodoEntity entity){
        
        if(entity == null){
            
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");    
        }
        
        if(entity.getUserId() == null){
            
            log.warn("Unknown User.");
            throw new RuntimeException("Unknown user.");
        }
    }
    
    public List<TodoEntity> create(final TodoEntity entity){
        
        validate(entity);

        todoRepository.save(entity);
        log.info("Entity Id : {} is saved.", entity.getId());

        return todoRepository.findByUserId(entity.getUserId());

    }

    public List<TodoEntity> retrieve(final String userId){
        return todoRepository.findByUserId(userId);
    }
}

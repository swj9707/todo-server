package com.example.todoserver.service;

import java.util.List;
import java.util.Optional;

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

    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);
    
        final Optional<TodoEntity> original = todoRepository.findById(entity.getId());
    
        original.ifPresent(todo -> {
          todo.setTitle(entity.getTitle());
          todo.setDone(entity.isDone());
    
          todoRepository.save(todo);
        });

        return retrieve(entity.getUserId());
      }

    public List<TodoEntity> delete(final TodoEntity entity) {
    validate(entity);

    try {
      todoRepository.delete(entity);
    } catch(Exception e) {
      log.error("error deleting entity ", entity.getId(), e);
      throw new RuntimeException("error deleting entity " + entity.getId());
    }
    log.info("Entity Id : {} is deleted.", entity.getId());
    return retrieve(entity.getUserId());
  }
}

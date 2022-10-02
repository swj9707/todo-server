package com.example.todoserver.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.todoserver.dto.ResponseDTO;
import com.example.todoserver.dto.TodoDTO;
import com.example.todoserver.entity.TodoEntity;
import com.example.todoserver.service.TodoService;

public class TodoControllerTest {

    @Autowired
    TodoService todoService;

    @Test
    ResponseEntity<?> testRetrieveTodoList() {
        try{
            String tmporaryUserId = "temporary-user";

            List<TodoEntity> entities = todoService.retrieve(tmporaryUserId);
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
        
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
        } catch(Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);

        }
    }
}

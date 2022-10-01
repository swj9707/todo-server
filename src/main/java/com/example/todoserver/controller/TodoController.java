package com.example.todoserver.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoserver.dto.ResponseDTO;
import com.example.todoserver.dto.TodoDTO;
import com.example.todoserver.entity.TodoEntity;
import com.example.todoserver.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
        try {

            String temporaryUserId = "temporary-user";
            TodoEntity entity = TodoDTO.dtoToEntity(dto);
            
            entity.setId(null);
            entity.setUserId(temporaryUserId);
            
            List<TodoEntity> entities = todoService.create(entity);
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
            
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);

        } catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            
            return ResponseEntity.badRequest().body(response);

        }
    }
    
    @GetMapping
    public ResponseEntity<?> retrieveTodoList(){
        String tmporaryUserId = "temporary-user";

        List<TodoEntity> entities = todoService.retrieve(tmporaryUserId);
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
        
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }
}

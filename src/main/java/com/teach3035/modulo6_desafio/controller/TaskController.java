package com.teach3035.modulo6_desafio.controller;

import com.teach3035.modulo6_desafio.DTO.res.GetTasksDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @GetMapping
    public GetTasksDTO getTasks(){
        return new GetTasksDTO(List.of());
    }
}

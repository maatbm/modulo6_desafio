package com.teach3035.modulo6_desafio.controller;

import com.teach3035.modulo6_desafio.DTO.req.GetTasksReqDTO;
import com.teach3035.modulo6_desafio.DTO.res.GetTaskByIdResDTO;
import com.teach3035.modulo6_desafio.DTO.res.GetTasksDTO;
import com.teach3035.modulo6_desafio.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    public GetTasksDTO getTasks(@RequestBody GetTasksReqDTO dto, @AuthenticationPrincipal UserDetails userDetails){
        return taskService.getTasks(dto, userDetails.getUsername());
    }

    @GetMapping("/{id}")
    public GetTaskByIdResDTO getTaskById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return taskService.getTaskById(id, userDetails.getUsername());
    }
}

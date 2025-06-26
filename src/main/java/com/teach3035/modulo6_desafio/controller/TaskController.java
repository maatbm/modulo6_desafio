package com.teach3035.modulo6_desafio.controller;

import com.teach3035.modulo6_desafio.dto.req.CreateTaskReqDTO;
import com.teach3035.modulo6_desafio.dto.req.UpdateTaskReqDTO;
import com.teach3035.modulo6_desafio.dto.res.GetTasksDTO;
import com.teach3035.modulo6_desafio.dto.res.TaskDTO;
import com.teach3035.modulo6_desafio.service.TaskService;
import jakarta.validation.Valid;
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
    public GetTasksDTO getTasks(@RequestParam(value = "status", required = false) String status, @AuthenticationPrincipal UserDetails userDetails) {
        return status == null
                ? taskService.getAllTasks(userDetails.getUsername())
                : taskService.getTasksWithStatusFilter(status, userDetails.getUsername());
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return taskService.getTaskById(id, userDetails.getUsername());
    }

    @PostMapping
    public TaskDTO createTask(@Valid @RequestBody CreateTaskReqDTO dto, @AuthenticationPrincipal UserDetails userDetails) {
        return taskService.createTask(dto, userDetails.getUsername());
    }

    @PatchMapping("/{id}")
    public TaskDTO updateTaskById(@PathVariable Long id, @RequestBody UpdateTaskReqDTO dto, @AuthenticationPrincipal UserDetails userDetails) {
        return taskService.PatchUpdateTaskById(id, dto, userDetails.getUsername());
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        taskService.deleteTaskById(id, userDetails.getUsername());
    }
}

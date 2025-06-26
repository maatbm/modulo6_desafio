package com.teach3035.modulo6_desafio.service;

import com.teach3035.modulo6_desafio.dto.req.CreateTaskReqDTO;
import com.teach3035.modulo6_desafio.dto.req.UpdateTaskReqDTO;
import com.teach3035.modulo6_desafio.dto.res.GetTasksDTO;
import com.teach3035.modulo6_desafio.dto.res.TaskDTO;
import com.teach3035.modulo6_desafio.exception.custom.InvalidStatusException;
import com.teach3035.modulo6_desafio.exception.custom.TaskNotFoundException;
import com.teach3035.modulo6_desafio.exception.custom.UserNotFoundException;
import com.teach3035.modulo6_desafio.model.TaskModel;
import com.teach3035.modulo6_desafio.model.UserModel;
import com.teach3035.modulo6_desafio.model.enums.TaskStatus;
import com.teach3035.modulo6_desafio.repository.TaskRepository;
import com.teach3035.modulo6_desafio.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public GetTasksDTO getAllTasks(String username, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskModel> tasks = taskRepository.findAllByUserUsername(username, pageable);
        return new GetTasksDTO(
                tasks.stream().map(task -> new TaskDTO(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus()
                )).toList()
        );
    }

    public GetTasksDTO getTasksWithStatusFilter(String status, String username, int page, int size) {
        TaskStatus taskStatus = this.validateStatus(status);
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskModel> tasks = taskRepository.findAllByUserUsernameAndStatus(username, taskStatus, pageable);
        return new GetTasksDTO(
                tasks.stream().map(task -> new TaskDTO(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus()
                )).toList()
        );
    }

    @Transactional
    public TaskDTO getTaskById(Long id, String username) {
        TaskModel task = taskRepository
                .findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }

    public TaskDTO createTask(CreateTaskReqDTO dto, String username) {
        UserModel user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        TaskModel task = new TaskModel();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(TaskStatus.PENDING);
        task.setUser(user);
        taskRepository.save(task);
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }

    public TaskDTO PatchUpdateTaskById(Long id, UpdateTaskReqDTO dto, String username) {
        TaskModel task = taskRepository
                .findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        if (dto.description() != null && !task.getDescription().equals(dto.description())) {
            task.setDescription(dto.description());
        }
        TaskStatus newStatus = this.validateStatus(dto.status());
        if (!task.getStatus().name().equals(newStatus.name())) {
            task.setStatus(newStatus);
        }
        taskRepository.save(task);
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }

    public void deleteTaskById(Long id, String username) {
        TaskModel task = taskRepository
                .findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        taskRepository.delete(task);
    }

    private TaskStatus validateStatus(String status) {
        try {
            return TaskStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusException("Invalid status: " + status);
        }
    }
}

package com.teach3035.modulo6_desafio.service;

import com.teach3035.modulo6_desafio.DTO.req.CreateTaskReqDTO;
import com.teach3035.modulo6_desafio.DTO.req.GetTasksReqDTO;
import com.teach3035.modulo6_desafio.DTO.res.GetTasksDTO;
import com.teach3035.modulo6_desafio.DTO.res.TaskDTO;
import com.teach3035.modulo6_desafio.exception.custom.TaskNotFoundException;
import com.teach3035.modulo6_desafio.model.TaskModel;
import com.teach3035.modulo6_desafio.model.UserModel;
import com.teach3035.modulo6_desafio.model.enums.TaskStatus;
import com.teach3035.modulo6_desafio.repository.TaskRepository;
import com.teach3035.modulo6_desafio.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public GetTasksDTO getTasks(GetTasksReqDTO dto, String username) {
        return new GetTasksDTO(List.of());
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
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty())
            throw new UsernameNotFoundException("User not found with username: " + username);
        UserModel user = optionalUser.get();
        TaskModel task = new TaskModel();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
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
}

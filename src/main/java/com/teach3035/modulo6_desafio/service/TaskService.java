package com.teach3035.modulo6_desafio.service;

import com.teach3035.modulo6_desafio.DTO.req.GetTasksReqDTO;
import com.teach3035.modulo6_desafio.DTO.res.GetTaskByIdResDTO;
import com.teach3035.modulo6_desafio.DTO.res.GetTasksDTO;
import com.teach3035.modulo6_desafio.DTO.res.TaskDTO;
import com.teach3035.modulo6_desafio.exception.custom.TaskNotFoundException;
import com.teach3035.modulo6_desafio.model.TaskModel;
import com.teach3035.modulo6_desafio.model.UserModel;
import com.teach3035.modulo6_desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private UserRepository userRepository;

    public GetTasksDTO getTasks(GetTasksReqDTO dto, String username) {
        return new GetTasksDTO(List.of());
    }

    public GetTaskByIdResDTO getTaskById(Long id, String username) {
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty())
            throw new UsernameNotFoundException("User not found with username: " + username);
        UserModel user = optionalUser.get();
        List<TaskModel> tasks = user.getTasks();
        TaskModel task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        TaskDTO response = new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
        return new GetTaskByIdResDTO(response);
    }
}

package com.teach3035.modulo6_desafio.repository;

import com.teach3035.modulo6_desafio.model.TaskModel;
import com.teach3035.modulo6_desafio.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    Optional<TaskModel> findByIdAndUserUsername(Long id, String username);

    List<TaskModel> findAllByUserUsername(String username);

    List<TaskModel> findAllByUserUsernameAndStatus(String username, TaskStatus taskStatus);
}

package com.teach3035.modulo6_desafio.repository;

import com.teach3035.modulo6_desafio.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
}

package com.teach3035.modulo6_desafio.DTO.res;

import com.teach3035.modulo6_desafio.model.enums.TaskStatus;

public record TaskDTO(
        Long id,
        String title,
        String description,
        TaskStatus status
) {
}

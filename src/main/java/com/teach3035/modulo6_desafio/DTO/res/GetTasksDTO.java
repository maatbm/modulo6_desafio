package com.teach3035.modulo6_desafio.DTO.res;

import java.util.List;

public record GetTasksDTO(
        List<TaskDTO> tasks
) {
}

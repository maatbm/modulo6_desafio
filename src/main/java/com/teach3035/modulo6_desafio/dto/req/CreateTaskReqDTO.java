package com.teach3035.modulo6_desafio.dto.req;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskReqDTO (
    @NotBlank(message = "Title cannot be blank")
    String title,

    String description
){}

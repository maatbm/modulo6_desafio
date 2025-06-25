package com.teach3035.modulo6_desafio.DTO.req;

import jakarta.validation.constraints.NotBlank;

public class CreateTaskReqDTO {
    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

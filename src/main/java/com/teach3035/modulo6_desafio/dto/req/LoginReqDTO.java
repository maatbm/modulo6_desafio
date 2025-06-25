package com.teach3035.modulo6_desafio.dto.req;

import jakarta.validation.constraints.NotBlank;

public class LoginReqDTO {
    // PRIMARY FIELDS
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    // GETTERS
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}

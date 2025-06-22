package com.teach3035.modulo6_desafio.controller;

import com.teach3035.modulo6_desafio.DTO.req.RegisterUserReqDTO;
import com.teach3035.modulo6_desafio.DTO.res.RegisterUserResDTO;
import com.teach3035.modulo6_desafio.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    // CREATE A NEW USER
    @PostMapping("/register")
    public RegisterUserResDTO register(@Valid @RequestBody RegisterUserReqDTO registerUserReqDTO) {
        return userService.registerUser(registerUserReqDTO);
    }
}

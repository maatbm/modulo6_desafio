package com.teach3035.modulo6_desafio.service;

import com.teach3035.modulo6_desafio.DTO.req.LoginReqDTO;
import com.teach3035.modulo6_desafio.DTO.req.RegisterUserReqDTO;
import com.teach3035.modulo6_desafio.DTO.res.LoginResDTO;
import com.teach3035.modulo6_desafio.DTO.res.RegisterUserResDTO;
import com.teach3035.modulo6_desafio.exception.custom.UserAlredyExistsExcpetion;
import com.teach3035.modulo6_desafio.exception.custom.UserNotFoundException;
import com.teach3035.modulo6_desafio.model.UserModel;
import com.teach3035.modulo6_desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public RegisterUserResDTO registerUser(RegisterUserReqDTO user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new UserAlredyExistsExcpetion("User already exists with username: " + user.getUsername());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        UserModel userModel = new UserModel(user.getUsername(), encryptedPassword);
        userRepository.save(userModel);
        return new RegisterUserResDTO(userModel.getId(), userModel.getUsername());
    }

    public LoginResDTO loginUser(LoginReqDTO loginReqDTO) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginReqDTO.getUsername(), loginReqDTO.getPassword());
        authenticationManager.authenticate(token);
        return tokenService.generateToken(loginReqDTO.getUsername());
    }
}

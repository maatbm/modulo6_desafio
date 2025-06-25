package com.teach3035.modulo6_desafio.service;

import com.teach3035.modulo6_desafio.dto.req.LoginReqDTO;
import com.teach3035.modulo6_desafio.dto.req.RegisterUserReqDTO;
import com.teach3035.modulo6_desafio.dto.res.LoginResDTO;
import com.teach3035.modulo6_desafio.dto.res.RegisterUserResDTO;
import com.teach3035.modulo6_desafio.exception.custom.UserAlredyExistsExcpetion;
import com.teach3035.modulo6_desafio.model.UserModel;
import com.teach3035.modulo6_desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        if (userRepository.existsByUsername(user.username()))
            throw new UserAlredyExistsExcpetion("User already exists with username: " + user.username());
        String encryptedPassword = passwordEncoder.encode(user.password());
        UserModel userModel = new UserModel(user.username(), encryptedPassword);
        userRepository.save(userModel);
        return new RegisterUserResDTO(userModel.getId(), userModel.getUsername());
    }

    public LoginResDTO loginUser(LoginReqDTO loginReqDTO) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginReqDTO.username(), loginReqDTO.password());
        authenticationManager.authenticate(token);
        return tokenService.generateToken(loginReqDTO.username());
    }
}

package com.teach3035.modulo6_desafio.service;

import com.teach3035.modulo6_desafio.DTO.req.LoginReqDTO;
import com.teach3035.modulo6_desafio.DTO.req.RegisterUserReqDTO;
import com.teach3035.modulo6_desafio.DTO.res.LoginResDTO;
import com.teach3035.modulo6_desafio.DTO.res.RegisterUserResDTO;
import com.teach3035.modulo6_desafio.exception.custom.InvalidPasswordException;
import com.teach3035.modulo6_desafio.exception.custom.UserAlredyExistsExcpetion;
import com.teach3035.modulo6_desafio.exception.custom.UserNotFoundException;
import com.teach3035.modulo6_desafio.model.UserModel;
import com.teach3035.modulo6_desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public RegisterUserResDTO registerUser(RegisterUserReqDTO user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new UserAlredyExistsExcpetion("User already exists with username: " + user.getUsername());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        UserModel userModel = new UserModel(user.getUsername(), encryptedPassword);
        userRepository.save(userModel);
        return new RegisterUserResDTO(userModel.getId(), userModel.getUsername());
    }

    public LoginResDTO loginUser(LoginReqDTO loginReqDTO) {
        Optional<UserModel> optionalUser = userRepository.findByUsername(loginReqDTO.getUsername());
        if (optionalUser.isEmpty())
            throw new UserNotFoundException("User not found with username: " + loginReqDTO.getUsername());
        UserModel user = optionalUser.get();
        if (!passwordEncoder.matches(loginReqDTO.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password for user: " + loginReqDTO.getUsername());
        }
        return tokenService.generateToken(user);
    }
}

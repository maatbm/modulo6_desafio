package com.teach3035.modulo6_desafio.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.teach3035.modulo6_desafio.dto.res.ExceptionResDTO;
import com.teach3035.modulo6_desafio.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResDTO resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        return new ExceptionResDTO(HttpStatus.NOT_FOUND.name(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ExceptionResDTO userAlreadyExistsExceptionHandler(UserAlreadyExistsException e) {
        return new ExceptionResDTO(HttpStatus.CONFLICT.name(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidStatusException.class)
    public ExceptionResDTO invalidTaskStatusExceptionHandler(InvalidStatusException e) {
        return new ExceptionResDTO(HttpStatus.BAD_REQUEST.name(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JWTVerificationException.class)
    public ExceptionResDTO jwtExceptionHandler(JWTVerificationException e) {
        return new ExceptionResDTO(HttpStatus.UNAUTHORIZED.name(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ExceptionResDTO badCredentialsExceptionHandler(BadCredentialsException e) {
        return new ExceptionResDTO(HttpStatus.UNAUTHORIZED.name(), e.getMessage());
    }
}

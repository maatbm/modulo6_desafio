package com.teach3035.modulo6_desafio.exception.custom;

public class UserNotFoundException extends ResourceNotFoundException{
    public UserNotFoundException(String message) {
        super(message);
    }
}

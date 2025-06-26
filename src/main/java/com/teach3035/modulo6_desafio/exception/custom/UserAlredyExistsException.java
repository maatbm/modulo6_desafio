package com.teach3035.modulo6_desafio.exception.custom;

public class UserAlredyExistsException extends RuntimeException {
    public UserAlredyExistsException(String message) {
        super(message);
    }
}

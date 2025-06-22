package com.teach3035.modulo6_desafio.exception.custom;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(String message) {
        super(message);
    }
}

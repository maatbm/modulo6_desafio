package com.teach3035.modulo6_desafio.exception.custom;

public class UserAlredyExistsExcpetion extends RuntimeException{
    public UserAlredyExistsExcpetion(String message) {
        super(message);
    }
}

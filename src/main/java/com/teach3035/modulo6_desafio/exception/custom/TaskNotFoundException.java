package com.teach3035.modulo6_desafio.exception.custom;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message) {
        super(message);
    }
}

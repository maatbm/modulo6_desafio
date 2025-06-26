package com.teach3035.modulo6_desafio.exception.custom;

public class TaskNotFoundException extends ResourceNotFoundException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}

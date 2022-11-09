package com.person.exception;

public class HobbyNotFoundException extends ResourceNotFoundException {

    public HobbyNotFoundException(String message) {
        super(message);
    }
}

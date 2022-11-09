package com.person.exception;

public class PersonNotFoundException extends ResourceNotFoundException {

    public PersonNotFoundException(String message) {
        super(message);
    }
}

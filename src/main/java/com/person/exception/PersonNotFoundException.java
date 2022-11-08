package com.person.exception;

public class PersonNotFoundException extends ResourceNotFoundException {
    public PersonNotFoundException() {
        super("Person not found");
    }
}

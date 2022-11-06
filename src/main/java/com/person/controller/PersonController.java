package com.person.controller;

import com.person.dto.CreatePersonRequest;
import com.person.dto.CreatePersonResponse;
import com.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePersonResponse> createPerson(@RequestBody @Valid CreatePersonRequest createPersonRequest) {
        return new ResponseEntity<>(personService.createPerson(createPersonRequest), HttpStatus.CREATED);
    }
}

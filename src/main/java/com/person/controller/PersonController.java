package com.person.controller;

import com.person.dto.CreatePersonRequest;
import com.person.dto.JSONResponse;
import com.person.dto.PersonResponse;
import com.person.dto.UpdatePersonRequest;
import com.person.helper.ResponseHelper;
import com.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> createPerson(@RequestBody @Valid CreatePersonRequest createPersonRequest, HttpServletRequest request) {
        PersonResponse personResponse = personService.createPerson(createPersonRequest);
        String personRef = personResponse.ref();
        return ResponseHelper.created("Person created.", personRef, request.getRequestURL(), personResponse);

    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> getPersons() {
        List<PersonResponse> persons = personService.getPersons();
        return ResponseHelper.ok(persons);
    }

    @GetMapping(path = "/{ref}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> getPersonByRef(@PathVariable String ref) {
        return ResponseHelper.ok(personService.getPerson(ref));
    }

    @PutMapping(path = "/{ref}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> updatePersonByRef(@PathVariable String ref, @RequestBody @Valid UpdatePersonRequest updatePersonRequest) {
        return ResponseHelper.ok(personService.updatePerson(ref, updatePersonRequest));
    }

    @DeleteMapping(path = "/{ref}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONResponse> deletePersonByRef(@PathVariable String ref) {
        personService.deletePerson(ref);
        return ResponseHelper.deleted("Person deleted");
    }
}

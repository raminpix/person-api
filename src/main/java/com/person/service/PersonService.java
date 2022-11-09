package com.person.service;

import com.person.dto.CreatePersonRequest;
import com.person.dto.PersonResponse;
import com.person.dto.UpdatePersonRequest;
import com.person.exception.PersonNotFoundException;
import com.person.mapper.PersonMapper;
import com.person.model.Person;
import com.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    private static final String PERSON_NOT_FOUND_MESSAGE = "Person with ref %s not found.";

    @Transactional
    public PersonResponse createPerson(CreatePersonRequest createPersonRequest) {
        Person person = personMapper.updateExistingPerson(createPersonRequest);
        Person createdPerson = personRepository.save(person);
        return personMapper.toPersonResponse(createdPerson);
    }

    @Transactional(readOnly = true)
    public List<PersonResponse> getPersons() {
        return personMapper.toPersonResponseList(personRepository.getAllPersons());
    }

    @Transactional(readOnly = true)
    public PersonResponse getPerson(String ref) {
        Person person = personRepository.findByRef(ref).orElseThrow(() -> new PersonNotFoundException(String.format(PERSON_NOT_FOUND_MESSAGE, ref)));
        return personMapper.toPersonResponse(person);
    }

    @Transactional
    public PersonResponse updatePerson(String ref, UpdatePersonRequest updatePersonRequest) {
        Person person = personRepository.findByRef(ref).orElseThrow(() -> new PersonNotFoundException(String.format(PERSON_NOT_FOUND_MESSAGE, ref)));
        personMapper.updateExistingPerson(updatePersonRequest, person);
        Person updatedPerson = personRepository.save(person);
        return personMapper.toPersonResponse(updatedPerson);
    }

    @Transactional
    public void deletePerson(String ref) {
        Person person = personRepository.findByRef(ref).orElseThrow(() -> new PersonNotFoundException(String.format(PERSON_NOT_FOUND_MESSAGE, ref)));
        personRepository.delete(person);
    }

}

package com.person.service;

import com.person.dto.CreatePersonRequest;
import com.person.dto.PersonResponse;
import com.person.dto.UpdatePersonRequest;
import com.person.exception.ColourNotFoundException;
import com.person.exception.HobbyNotFoundException;
import com.person.exception.PersonNotFoundException;
import com.person.mapper.PersonMapper;
import com.person.model.Colour;
import com.person.model.Hobby;
import com.person.model.Person;
import com.person.repository.ColourRepository;
import com.person.repository.HobbyRepository;
import com.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Log
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final ColourRepository colourRepository;
    private final HobbyRepository hobbyRepository;
    private final PersonMapper personMapper;

    @Transactional
    public PersonResponse createPerson(CreatePersonRequest createPersonRequest) {
        Person person = personMapper.toPerson(createPersonRequest);
        Colour colour = colourRepository.findByColourName(createPersonRequest.favouriteColour()).orElseThrow(ColourNotFoundException::new);
        person.setFavouriteColour(colour);
        Set<Hobby> hobbies = hobbyRepository.findByHobbyNameIn(Set.of(createPersonRequest.hobbies())).orElseThrow(HobbyNotFoundException::new);
        person.setHobbies(hobbies);
        Person createdPerson = personRepository.save(person);
        return personMapper.toPersonResponse(createdPerson);
    }

    public List<PersonResponse> getPersons() {
        return personMapper.toPersonResponseList(personRepository.getAllPersons());
    }

    public PersonResponse getPerson(String ref) {
        Person person = personRepository.findByRef(ref).orElseThrow(PersonNotFoundException::new);
        return personMapper.toPersonResponse(person);
    }

    public PersonResponse updatePerson(String ref, UpdatePersonRequest updatePersonRequest) {
        Person person = personRepository.findByRef(ref).orElseThrow(PersonNotFoundException::new);
        person.setFirstName(updatePersonRequest.firstName());
        person.setLastName(updatePersonRequest.lastName());
        person.setBirthDate(updatePersonRequest.birthDate());
        Colour colour = colourRepository.findByColourName(updatePersonRequest.favouriteColour()).orElseThrow(ColourNotFoundException::new);
        person.setFavouriteColour(colour);
        Set<Hobby> hobbies = hobbyRepository.findByHobbyNameIn(Set.of(updatePersonRequest.hobbies())).orElseThrow(HobbyNotFoundException::new);
        person.setHobbies(hobbies);
        Person updatedPerson = personRepository.save(person);
        return personMapper.toPersonResponse(updatedPerson);
    }

    public void deletePerson(String ref) {
        Person person = personRepository.findByRef(ref).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
    }
}

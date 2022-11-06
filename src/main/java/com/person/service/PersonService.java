package com.person.service;

import com.person.dto.CreatePersonRequest;
import com.person.dto.CreatePersonResponse;
import com.person.exception.ColourNotFoundException;
import com.person.exception.HobbyNotFoundException;
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
    public CreatePersonResponse createPerson(CreatePersonRequest createPersonRequest) {
        Person person = personMapper.toPerson(createPersonRequest);
        Colour colour = colourRepository.findByColourName(createPersonRequest.favouriteColour()).orElseThrow(ColourNotFoundException::new);
        person.setFavouriteColor(colour);
        Set<Hobby> hobbies = hobbyRepository.findByHobbyNameIn(Set.of(createPersonRequest.hobbies())).orElseThrow(HobbyNotFoundException::new);
        person.setHobbies(hobbies);
        Person createdPerson = personRepository.save(person);
        return personMapper.toCreatePersonResponse(createdPerson);
    }
}

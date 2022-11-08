package com.person.mapper;

import com.person.dto.CreatePersonRequest;
import com.person.dto.PersonResponse;
import com.person.dto.UpdatePersonRequest;
import com.person.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(target = "hobbies", ignore = true)
    @Mapping(target = "favouriteColour", ignore = true)
    Person toPerson(CreatePersonRequest createPersonRequest);

    @Mapping(target = "hobbies", source = "hobbyNames")
    @Mapping(target = "favouriteColour", source = "favouriteColour.colourName")
    PersonResponse toPersonResponse(Person person);

    List<PersonResponse> toPersonResponseList(List<Person> personList);

    @Mapping(target = "hobbies", ignore = true)
    @Mapping(target = "favouriteColour", ignore = true)
    Person toPerson(UpdatePersonRequest updatePersonRequest);

}
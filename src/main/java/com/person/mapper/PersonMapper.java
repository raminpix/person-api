package com.person.mapper;

import com.person.dto.CreatePersonRequest;
import com.person.dto.CreatePersonResponse;
import com.person.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(target = "hobbies", ignore = true)
    @Mapping(target = "favouriteColor", ignore = true)
    Person toPerson(CreatePersonRequest createPersonRequest);

    @Mapping(target = "hobbies", source = "hobbyNames")
    @Mapping(target = "favouriteColour", source = "favouriteColor.colourName")
    CreatePersonResponse toCreatePersonResponse(Person person);

}
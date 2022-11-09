package com.person.mapper;

import com.person.dto.CreatePersonRequest;
import com.person.dto.PersonResponse;
import com.person.dto.UpdatePersonRequest;
import com.person.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {

    @Autowired
    protected HobbyMapper hobbyMapper;

    @Autowired
    protected ColourMapper colourMapper;

    @Mapping(target = "hobbies", expression = "java(hobbyMapper.toHobbies(createPersonRequest.hobbies()))")
    @Mapping(target = "favouriteColour", expression = "java(colourMapper.toColour(createPersonRequest.favouriteColour()))")
    public abstract Person updateExistingPerson(CreatePersonRequest createPersonRequest);

    @Mapping(target = "hobbies", source = "hobbyNames")
    @Mapping(target = "favouriteColour", source = "favouriteColour.colourName")
    public abstract PersonResponse toPersonResponse(Person person);

    public abstract List<PersonResponse> toPersonResponseList(List<Person> personList);


    @Mapping(target = "hobbies",
            expression = "java(updatePersonRequest.hobbies() != null ? hobbyMapper.toHobbies(updatePersonRequest.hobbies()) : person.getHobbies())")
    @Mapping(target = "favouriteColour",
            expression = "java(updatePersonRequest.favouriteColour() != null ? colourMapper.toColour(updatePersonRequest.favouriteColour()) : person.getFavouriteColour())")
    @Mapping(target = "firstName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "lastName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "birthDate", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateExistingPerson(UpdatePersonRequest updatePersonRequest, @MappingTarget Person person);
}
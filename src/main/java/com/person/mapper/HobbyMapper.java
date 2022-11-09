package com.person.mapper;

import com.person.exception.HobbyNotFoundException;
import com.person.model.Hobby;
import com.person.repository.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class HobbyMapper {

    private final HobbyRepository hobbyRepository;
    private static final String HOBBIES_NOT_FOUND_MESSAGE = "Hobbies with name %s not found.";


    public Set<Hobby> toHobbies(String[] hobbyNames) {
        return hobbyRepository.findByHobbyNameIn(Set.of(hobbyNames)).orElseThrow(() -> new HobbyNotFoundException(String.format(HOBBIES_NOT_FOUND_MESSAGE, hobbyNames)));
    }

}

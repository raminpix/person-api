package com.person.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * A DTO for responding created {@link com.person.model.Person} entity
 */
public record CreatePersonResponse(String ref, String firstName, String lastName, LocalDate birthDate,
                                   String favouriteColour, Set<String> hobbies) implements Serializable {
}
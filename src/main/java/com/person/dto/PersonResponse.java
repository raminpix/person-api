package com.person.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * A DTO for responding created {@link com.person.model.Person} entity
 */
public record PersonResponse(String ref, String firstName, String lastName, long age,
                             String favouriteColour, Set<String> hobbies) implements Serializable {
}
package com.person.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.person.model.Person} entity
 */
public record CreatePersonRequest(String firstName, String lastName, LocalDate birthDate,
                                  String favouriteColorColour) implements Serializable {
}
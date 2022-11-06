package com.person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for creating {@link com.person.model.Person} entity
 */
public record CreatePersonRequest(@JsonProperty("first_name") String firstName,
                                  @JsonProperty("last_name") String lastName,
                                  @JsonProperty("birth_date") @JsonFormat(pattern = "yyyy/MM/dd") LocalDate birthDate,
                                  @JsonProperty("favourite_colour") String favouriteColour,
                                  @JsonProperty("hobbies") String[] hobbies) implements Serializable {
    // TODO : Equals and HashCode
}
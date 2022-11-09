package com.person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for creating {@link com.person.model.Person} entity
 */
public record CreatePersonRequest(
        @NotEmpty(message = "first_name should not be null") @JsonProperty("first_name") String firstName,
        @NotEmpty(message = "last_name should not be null") @JsonProperty("last_name") String lastName,
        @NotNull(message = "birth_date should not be null") @JsonProperty("birth_date") @JsonFormat(pattern = "yyyy/MM/dd") LocalDate birthDate,
        @NotEmpty(message = "favourite_colour should not be null") @JsonProperty("favourite_colour") String favouriteColour,
        @NotEmpty(message = "hobbies should not be null") @JsonProperty("hobbies") String[] hobbies) implements Serializable {
}
package com.person;


import com.person.model.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonModelTest {

    @Test
    void shouldCalculateCorrectAge() {
        //Given
        Person person = new Person();

        //Now represents the startDate of calculating age to execute the tests without dependency on currentDate
        LocalDate now = LocalDate.of(2022,11,5);

        //When
        person.setBirthDate(LocalDate.of(1986,9,1));

        //Then
        assertEquals(person.getAge(now), 36);
    }
}

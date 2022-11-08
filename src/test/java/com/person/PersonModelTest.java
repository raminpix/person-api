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

        //When
        person.setBirthDate(LocalDate.of(1986,9,1));

        //Then
        // TODO : Use hamcrest
        assertEquals(person.getAge(), 36);
    }
}

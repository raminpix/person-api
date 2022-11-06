package com.person.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person_hobby")
public class PersonHobby {

    @EmbeddedId
    private PersonHobbyId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("hobbyId")
    private Hobby hobby;

    private PersonHobby() {
    }

    public PersonHobby(Person person, Hobby hobby) {
        this.person = person;
        this.hobby = hobby;
        this.id = new PersonHobbyId(person.getId(), hobby.getId());
    }

    //Getters and setters omitted for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PersonHobby other = (PersonHobby) o;
        return Objects.equals(person, other.person) &&
                Objects.equals(hobby, other.hobby);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, hobby);
    }
}

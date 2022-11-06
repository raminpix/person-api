package com.person.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonHobbyId implements Serializable {

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "hobby_id")
    private Long hobbyId;

    private PersonHobbyId() {
    }

    public PersonHobbyId(Long personId, Long hobbyId) {
        this.personId = personId;
        this.hobbyId = hobbyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        PersonHobbyId other = (PersonHobbyId) o;
        return Objects.equals(personId, other.personId) &&
                Objects.equals(hobbyId, other.hobbyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, hobbyId);
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Long hobbyId) {
        this.hobbyId = hobbyId;
    }
}
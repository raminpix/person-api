package com.person.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {

    @Id // Technical ID used as table primary key and used in relations
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    /*
     Since the technical ID (id) is sequential and predictable and is not secure, ref will be used
     as an identifier (Natural ID) for communication outside the API.
    */
    @NaturalId
    @Column(name = "ref", nullable = false, unique = true, length = 8)
    private String ref;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 40)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "favourite_colour_id")
    private Colour favouriteColor;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "person_hobby",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id")
    )
    private Set<Hobby> hobbies = new HashSet<>();

    public Person() {
        // Default Constructor for JPA Entity class
    }

    // TODO : Equals and HashCode !

    @PrePersist
    private void setPersonId() {
        ref = RandomStringUtils.randomAlphanumeric(8);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Colour getFavouriteColor() {
        return favouriteColor;
    }

    public void setFavouriteColor(Colour favouriteColor) {
        this.favouriteColor = favouriteColor;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public Set<String> getHobbyNames() {
        Set<String> hobbyNames = new HashSet<>();
        hobbies.forEach(c -> hobbyNames.add(c.getHobbyName()));
        return hobbyNames;
    }

    public Object[] getHobbiesAsArray() {
        return hobbies.toArray();
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    /*
            Java Doc for ....
         */
    public long getAge(LocalDate referenceDate) {
        if (birthDate == null) {
            return 0;
        }
        // If referenceDate is null, calculates age based on the currentDate
        return ChronoUnit.YEARS.between(birthDate, Objects.requireNonNullElseGet(referenceDate, LocalDate::now));
    }
}

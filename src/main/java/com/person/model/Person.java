package com.person.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {

    @Id // Technical ID used as table primary key and used in relations
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
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
    @JoinColumn(name = "favourite_color_id")
    private Colour favouriteColor;

    public Person() {
        // Default Constructor for JPA Entity class
    }

    // TODO : Equals and HashCode !

    @PrePersist
    private void setPersonId() {
        ref = UUID.randomUUID().toString();
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

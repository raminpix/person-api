package com.person.model;

import javax.persistence.*;

@Entity
@Table(name = "colour")
public class Colour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "colour_name", unique = true, nullable = false, length = 15)
    private String colourName;

    public Colour() {
        // Default Constructor for JPA Entity class
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Colour colour = (Colour) o;

        return colourName.equals(colour.colourName);
    }

    @Override
    public int hashCode() {
        return colourName.hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColourName() {
        return colourName;
    }

    public void setColourName(String colourName) {
        this.colourName = colourName;
    }
}

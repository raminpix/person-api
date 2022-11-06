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

    // TODO : Equals and HashCode !

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

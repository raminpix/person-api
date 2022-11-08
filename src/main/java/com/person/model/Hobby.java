package com.person.model;

import javax.persistence.*;

@Entity
@Table(name = "hobby")
public class Hobby {

    @Id // Technical ID used as table primary key and used in relations
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "hobby_name", unique = true, nullable = false, length = 30)
    private String hobbyName;

    public Hobby() {
        // Default Constructor for JPA Entity class
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }
}

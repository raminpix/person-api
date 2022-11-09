package com.person.repository;

import com.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT DISTINCT p from Person p join p.favouriteColour c join p.hobbies h WHERE p.ref = ?1")
    Optional<Person> findByRef(String personRef);

    @Query("SELECT DISTINCT p from Person p join fetch p.favouriteColour c join fetch p.hobbies h")
    List<Person> getAllPersons();
}
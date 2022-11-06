package com.person.repository;

import com.person.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
    Optional<Set<Hobby>> findByHobbyNameIn(Set<String> hobbyName);
}
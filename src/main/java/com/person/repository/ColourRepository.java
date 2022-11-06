package com.person.repository;

import com.person.model.Colour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColourRepository extends JpaRepository<Colour, Long> {

    Optional<Colour> findByColourName(String colourName);
}
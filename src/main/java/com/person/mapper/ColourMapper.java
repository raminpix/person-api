package com.person.mapper;

import com.person.exception.ColourNotFoundException;
import com.person.model.Colour;
import com.person.repository.ColourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ColourMapper {

    private final ColourRepository colourRepository;
    private static final String COLOUR_NOT_FOUND_MESSAGE = "Colour with name %s not found.";

    public Colour toColour(String colourName) {
        return colourRepository.findByColourName(colourName).orElseThrow(() -> new ColourNotFoundException(String.format(COLOUR_NOT_FOUND_MESSAGE, colourName)));
    }

}

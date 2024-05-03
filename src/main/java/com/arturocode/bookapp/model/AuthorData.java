package com.arturocode.bookapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorData(
        String name,
        Integer birth_year
) {
}

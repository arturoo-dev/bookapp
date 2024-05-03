package com.arturocode.bookapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksData(
        String title,
        List<AuthorData> authors,
        List<String> languages,
        Integer download_count
) {
}

package com.arturocode.bookapp.main;

import com.arturocode.bookapp.model.Books;
import com.arturocode.bookapp.model.BooksData;
import com.arturocode.bookapp.service.ConvertData;
import com.arturocode.bookapp.service.RequestAPI;

import java.util.Comparator;


public class Main {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private final RequestAPI requestAPI = new RequestAPI();
    private final ConvertData convertData = new ConvertData();

    public void showMenu() {
        var jsonRequest = requestAPI.requestData(URL_BASE);
        //System.out.println(jsonRequest);

        var dataBooks = convertData.convertData(jsonRequest, Books.class);
        System.out.println(dataBooks);

        System.out.println("Los 10 libros mas descargados son:");
        dataBooks.books().stream()
                .sorted(Comparator.comparing(BooksData::download_count).reversed())
                .limit(10)
                .map(l -> String.format("Titulo: %s || Total de descargas: %d", l.title().toUpperCase(), l.download_count()))
                .forEach(System.out::println);
    }
}

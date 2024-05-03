package com.arturocode.bookapp.main;

import com.arturocode.bookapp.model.Books;
import com.arturocode.bookapp.model.BooksData;
import com.arturocode.bookapp.service.ConvertData;
import com.arturocode.bookapp.service.RequestAPI;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private final RequestAPI requestAPI = new RequestAPI();
    private final ConvertData convertData = new ConvertData();
    private final String jsonRequest = requestAPI.requestData(URL_BASE);
    private final Books dataBooks = convertData.convertData(jsonRequest, Books.class);
    Scanner input = new Scanner(System.in);
    int option = 0;

    public void showMenu() {

        String menu = ("""
                \nBienvenidos a mi aplicacion!
                ===========================================
                1. Mostrar los 10 libros mas descargados
                2. Buscar un libro por nombre.
                3. Mostrar estadisticas.
                4. Salir.
                ===========================================
                Ingrese una opcion correcta.
                """);

        while (option != 4) {
            System.out.println(menu);
            option = input.nextInt();

            switch (option) {
                case 1:
                    topTen();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    statistics();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema.....");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }
        }
    }

    public void topTen() {
        System.out.println("Los 10 libros mas descargados son:");
        dataBooks.books().stream()
                .sorted(Comparator.comparing(BooksData::download_count).reversed())
                .limit(10)
                .map(l -> String.format("Titulo: %s || Total de descargas: %d", l.title().toUpperCase(), l.download_count()))
                .forEach(System.out::println);
    }

    public void searchBook() {
        System.out.print("Ingrese el nombre del libro: ");
        var title = input.next();
        var jsonRequest = requestAPI.requestData(URL_BASE + "?search=" + title.replace(" ", "+"));
        var searchResult = convertData.convertData(jsonRequest, Books.class);
        Optional<BooksData> foundBook = searchResult.books().stream()
                .filter(l -> l.title().toUpperCase().contains(title.toUpperCase()))
                .findFirst();
        if (foundBook.isEmpty()) {
            System.out.println("Libro no encontrado");
        } else {
            System.out.println("Libro encontrado");
            System.out.println(foundBook.get());
            ;
        }
    }

    public void statistics() {

    }
}

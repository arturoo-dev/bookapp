package com.arturocode.bookapp;

import com.arturocode.bookapp.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookappApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BookappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main();
        main.showMenu();
    }
}

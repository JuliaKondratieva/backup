package com.julieandco.bookservice.config;

import com.julieandco.bookservice.entities.Book;
import com.julieandco.bookservice.entities.Genre;
import com.julieandco.bookservice.repo.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AppConfig {
    /*@Bean
    public CommandLineRunner demo(final BookRepository bookRepository) {
        return strings -> {
            Book book1 = new Book("11/22/63", "Stephen King", 2011, 8, Genre.Fantasy);
            bookRepository.save(book1);

        };
    }*/
}

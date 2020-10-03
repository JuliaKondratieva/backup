package com.julieandco.bookservice.controller;

import com.julieandco.bookservice.entities.Book;
import com.julieandco.bookservice.entities.Genre;
import com.julieandco.bookservice.entities.User;
import com.julieandco.bookservice.entities.dto.BookDTO;
import com.julieandco.bookservice.entities.dto.BooksDTO;
import com.julieandco.bookservice.entities.dto.UserDTO;
import com.julieandco.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public @ResponseBody
    BooksDTO getAllProducts(){
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setBooks(bookService.getAllBooks());
        return booksDTO;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveBook(@RequestBody BookDTO bookDTO){
        String title = bookDTO.getTitle();
        String author=bookDTO.getAuthor();
        Genre genre = bookDTO.getGenre();
        long year =bookDTO.getYear();
        double rating=bookDTO.getRating();
        Book newBook = new Book(title, author, year, rating, genre);
        bookService.addBook(newBook);

        return ResponseEntity.ok().build();
    }
}

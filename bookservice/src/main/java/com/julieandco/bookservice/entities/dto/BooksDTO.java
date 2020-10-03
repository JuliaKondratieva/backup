package com.julieandco.bookservice.entities.dto;

import com.julieandco.bookservice.entities.Book;

import java.util.List;

public class BooksDTO {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

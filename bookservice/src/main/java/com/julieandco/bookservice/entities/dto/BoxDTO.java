package com.julieandco.bookservice.entities.dto;

import com.julieandco.bookservice.entities.Book;

import java.util.List;
import java.util.UUID;

public class BoxDTO {
    private List<Book> books;
    private String address;


    public BoxDTO() {
    }

    public BoxDTO(List<Book> books) {
        this.books = books;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}

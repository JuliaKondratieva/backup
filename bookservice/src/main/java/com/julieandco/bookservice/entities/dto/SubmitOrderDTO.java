package com.julieandco.bookservice.entities.dto;

import com.julieandco.bookservice.entities.Book;
import com.julieandco.bookservice.entities.User;

import java.util.List;

public class SubmitOrderDTO {
    private User user;
    private Book book;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

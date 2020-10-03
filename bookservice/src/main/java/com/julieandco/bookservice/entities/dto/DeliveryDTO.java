package com.julieandco.bookservice.entities.dto;

import com.julieandco.bookservice.entities.Book;
import com.julieandco.bookservice.entities.Bookorder;
import com.julieandco.bookservice.entities.Box;

import java.util.List;

public class DeliveryDTO {
    //private Bookorder bookorder;
    private Book book;
    private Box box;

    public DeliveryDTO(Bookorder bookorder, Book book) {
        this.book = book;
        this.box= box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    //public Bookorder getBookorder() {
    //  return bookorder;
    //}

    //public void setBookorder(Bookorder bookorder) {
    //  this.bookorder = bookorder;
    //}


    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public Box getBox() {
        return box;
    }
}

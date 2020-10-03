package com.julieandco.bookservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name="bookorder")
public class Bookorder {
    @Id
    @GeneratedValue
    private UUID id;
    //@JsonBackReference(value = "book")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Book book;
    //@JsonBackReference(value = "customer")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User customer;
    @Column
    private LocalDate fromDate;
    @Column
    private LocalDate dueDate;
    @Column
    private boolean deliveryState;
    @Column
    private boolean submitted;

    public Bookorder()
    {
        //book=new Book();
        //customer=new User();
        //deliveryState=false;
        //submitted=true;
    }

    public Bookorder(Book book, User user){
        this.book=book;
        this.customer=user;
        this.fromDate=LocalDate.now();
        this.dueDate=fromDate.plusWeeks(3);
        this.deliveryState=false;
        this.submitted=true;
    }

    public Book getBook(){
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public UUID getId() {
        return id;
    }

    public boolean getSubmitted() {
        return submitted;
    }

    public void setDeliveryState(boolean deliveryState) {
        this.deliveryState = deliveryState;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public User getUser(){
        return customer;
    }

    public void setUser(User user) {
        this.customer = user;
    }

    public void isDelivered(){
        this.deliveryState=true;
    }

    public boolean getDeliveryState(){
        return deliveryState;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }

    public String toString(){
        return "Book: " + book.getTitle() + "\r\n User: " + customer.getUsername() + customer.getIdNumber().toString() + "\r\n Order date: " + fromDate.toString() + "\r\n due Date: " + dueDate.toString();
    }
}

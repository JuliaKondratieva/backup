package com.julieandco.bookservice.entities;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name="bookorder")
public class Bookorder {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private User customer;
    @Column
    private LocalDateTime fromDate;
    @Column
    private LocalDateTime dueDate;
    @Column
    private boolean deliveryState;
    @Column
    private boolean submitted;

    public Bookorder()
    {

    }

    public Bookorder(Book book, User user){
        this.book=book;
        this.customer=user;
        this.fromDate=LocalDateTime.now();
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

    public LocalDateTime getFromDate() {
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

    public void setFromDate(LocalDateTime fromDate) {
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

    public LocalDateTime getDueDate(){
        return dueDate;
    }

    public String toString(){
        return "Book: " + book.getTitle() + "\r\n User: " + customer.getUsername() + customer.getIdNumber().toString() + "\r\n Order date: " + fromDate.toString() + "\r\n due Date: " + dueDate.toString();
    }
}

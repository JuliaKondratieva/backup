package com.julieandco.bookservice.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name="customer")
public class User {
    @Id
    @GeneratedValue
    private UUID idNumber;
    @Column
    private String username;

    @OneToMany(mappedBy="customer")
    private List<Bookorder> bookorder;


    public User() {
        username = "";

    }

    public List<Bookorder> getBookorder() {
        return bookorder;
    }

    public void setBookorder(List<Bookorder> order) {
        this.bookorder = order;
    }

    public User(String username) {
        this.username = username;
    }

    public UUID getIdNumber() {
        return idNumber;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

}

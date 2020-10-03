package com.julieandco.bookservice.entities.dto;

import com.julieandco.bookservice.entities.User;

import java.util.List;

public class UsersDTO {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

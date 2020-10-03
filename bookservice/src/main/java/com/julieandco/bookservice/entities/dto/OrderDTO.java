package com.julieandco.bookservice.entities.dto;

import com.julieandco.bookservice.entities.Bookorder;

import java.util.List;

public class OrderDTO {
    private List<Bookorder> bookorders;

    public OrderDTO() {
    }

    public OrderDTO(List<Bookorder> bookorders) {
        this.bookorders = bookorders;
    }

    public List<Bookorder> getOrders() {
        return bookorders;
    }

    public void setOrders(List<Bookorder> bookorders) {
        this.bookorders = bookorders;
    }
}

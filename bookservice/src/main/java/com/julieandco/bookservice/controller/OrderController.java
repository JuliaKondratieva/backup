package com.julieandco.bookservice.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.julieandco.bookservice.entities.Book;
import com.julieandco.bookservice.entities.User;
import com.julieandco.bookservice.entities.dto.OrderDTO;
import com.julieandco.bookservice.entities.dto.SubmitOrderDTO;
import com.julieandco.bookservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookorders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Void> submitOrder(@RequestBody String deliverJson){
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        SubmitOrderDTO deliver = gson.fromJson(deliverJson, SubmitOrderDTO.class);
        User user = deliver.getUser();
        Book book = deliver.getBook();
        orderService.addOrder(book, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public @ResponseBody
    OrderDTO getAllOrders(){
        OrderDTO ordersDTO = new OrderDTO();
        ordersDTO.setOrders(orderService.getAllOrders());
        return ordersDTO;
    }
}

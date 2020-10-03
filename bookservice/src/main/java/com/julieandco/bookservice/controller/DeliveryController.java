package com.julieandco.bookservice.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.julieandco.bookservice.entities.Book;
import com.julieandco.bookservice.entities.Bookorder;
import com.julieandco.bookservice.entities.Box;
import com.julieandco.bookservice.entities.dto.DeliveryDTO;
import com.julieandco.bookservice.service.BookService;
import com.julieandco.bookservice.service.BoxService;
import com.julieandco.bookservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RestController
@RequestMapping("/api")
public class DeliveryController {
    private BoxService boxService;
    private BookService bookService;
    private OrderService orderService;

    @Autowired
    public DeliveryController(BoxService boxService, BookService bookService, OrderService orderService) {
        this.boxService = boxService;
        this.bookService = bookService;
        this.orderService=orderService;
    }

    @PostMapping("/deliver")
    public ResponseEntity<Void> deliverProducts(@RequestBody String deliverJson) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DeliveryDTO deliver = gson.fromJson(deliverJson, DeliveryDTO.class);
        Book book = deliver.getBook();
        Box putIn = deliver.getBox();
        System.out.println(book);
        System.out.println(putIn);
        Box foundBox = boxService.findByAddress(putIn.getAddress());
        Book foundBook = bookService.findByTitle(book.getTitle());
        System.out.println(foundBox);
        System.out.println(foundBook);
        boxService.addBook(foundBox, foundBook);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<Void> checkOut(@RequestBody String deliverJson) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DeliveryDTO deliver = gson.fromJson(deliverJson, DeliveryDTO.class);

        Book bookOut = deliver.getBook();
        Box takeOut = deliver.getBox();
        Box foundBox = boxService.findByAddress(takeOut.getAddress());
        Book foundBook = bookService.findByTitle(bookOut.getTitle());
        List<Bookorder> toPickup = orderService.findByBookId(foundBook.getId());
        Bookorder checkedOut=new Bookorder();
        for(Bookorder o: toPickup){
            if(o.getSubmitted())
                checkedOut = o;
        }

        boxService.checkOut(foundBox, checkedOut);
        return ResponseEntity.ok().build();
    }

}

package com.julieandco.bookservice.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.julieandco.bookservice.entities.Book;
import com.julieandco.bookservice.entities.Bookorder;
import com.julieandco.bookservice.entities.Box;
import com.julieandco.bookservice.entities.dto.DeliveryDTO;
import com.julieandco.bookservice.service.BookService;
import com.julieandco.bookservice.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/api")
public class DeliveryController {
    private BoxService boxService;
    private BookService bookService;

    @Autowired
    public DeliveryController(BoxService boxService, BookService bookService) {
        this.boxService = boxService;
        this.bookService = bookService;
    }

    @PostMapping("/deliver")
    public ResponseEntity<Void> deliverProducts(@RequestBody String deliverJson){
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DeliveryDTO deliver = gson.fromJson(deliverJson, DeliveryDTO.class);
        //Bookorder toStorage = deliver.getBookorder();
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
}

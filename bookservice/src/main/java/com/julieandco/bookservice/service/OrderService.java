package com.julieandco.bookservice.service;

import com.julieandco.bookservice.entities.Book;
import com.julieandco.bookservice.entities.Bookorder;
import com.julieandco.bookservice.entities.Box;
import com.julieandco.bookservice.entities.User;
import com.julieandco.bookservice.repo.BookRepository;
import com.julieandco.bookservice.repo.OrderRepository;
import com.julieandco.bookservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(BookRepository bookRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void addOrder(Book orderedBook, User user){
        Book bookel=bookRepository.findBookByTitle(orderedBook.getTitle());
        User user1=userRepository.findByUsername(user.getUsername());
        Bookorder newOrder;
        newOrder = null;
        if(bookel==null){
            System.out.println("The book doesn't exist. Cannot create an order\n");
        }

        else if(bookel.getAvailability()) {
            newOrder = new Bookorder(bookel, user1);
            orderRepository.save(newOrder);
            bookel.setAvailable(false);
            bookRepository.save(bookel);
        }

        else{
            newOrder=new Bookorder(bookel, user1);
            newOrder.setSubmitted(false);
            orderRepository.save(newOrder);
            System.out.println("You are in waiting list \n");
        }
    }

    @Transactional
    public List<Bookorder> findByBookId(UUID id){
        return orderRepository.findByBookId(id);
    }


    @Transactional(readOnly = true)
    public List<Bookorder> getAllOrders() {
        return orderRepository.findAll();
    }
}

package com.julieandco.bookservice.service;

import com.julieandco.bookservice.entities.Book;
import com.julieandco.bookservice.entities.Box;
import com.julieandco.bookservice.entities.Bookorder;
import com.julieandco.bookservice.repo.BookRepository;
import com.julieandco.bookservice.repo.BoxRepository;
import com.julieandco.bookservice.repo.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Service
public class BoxService {
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final BoxRepository boxRepository;

    public BoxService(BookRepository bookRepository, OrderRepository orderRepository, OrderService orderService, BoxRepository boxRepository) {
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.orderService=orderService;
        this.boxRepository=boxRepository;
    }

    @Transactional
    public void addBox(Box box){
        if(boxRepository.findByAddress(box.getAddress()) == null){
            boxRepository.save(box);
        }
    }

    @Transactional
    public Box findByAddress(String address){
        return boxRepository.findByAddress(address);
    }


    @Transactional
    public void addBook(Box box, Book receivedBook) {
        //boxRepository.getOne(box.getId()).getBooks().add(receivedBook);
        //box.getBooks().add(receivedBook);
        Bookorder recOrder = null;
        List<Bookorder> byBookId = new ArrayList<>();
        List<Bookorder> nextOrderQueue = new ArrayList<>();

        Bookorder nextOrder = null;
        byBookId=orderRepository.findByBookId(receivedBook.getId());
        for(Bookorder o: byBookId){
            if(o.getSubmitted())
                recOrder=o;
        }

        if(recOrder!=null){
            Optional<Box> toBox = boxRepository.findById(box.getId());
            if(toBox.isEmpty())
                return;
            Box newToBox = toBox.get();
            recOrder.getBook().setBoxId(newToBox);
            bookRepository.save(recOrder.getBook());

            nextOrderQueue=orderRepository.findByBookId(receivedBook.getId());
            if(!nextOrderQueue.isEmpty()) {
                LocalDate min = LocalDate.now();
                for (Bookorder o : nextOrderQueue) {
                    if (min.isAfter(o.getFromDate())&&o!=recOrder) {
                        min = o.getFromDate();
                        nextOrder = o;
                    }
                }
            }
            if(recOrder.getDeliveryState()) {
                //nextOrder=orderRepository.findByBookIdAndMinFromDate(receivedBook.getId());
                if (nextOrder == null) {
                    recOrder.getBook().setAvailable(true);
                    orderRepository.delete(recOrder);
                } else {
                    orderRepository.delete(recOrder);
                    nextOrder.setSubmitted(true);
                    nextOrder.setFromDate(LocalDate.now());
                    orderRepository.save(nextOrder);
                    System.out.println(nextOrder.getUser().getUsername() + ", Your Order was submitted. Expect!");
                }
            }
            //ordermovequeue
        }
    }

    @Transactional
    public void checkOut(Box box, Bookorder bookorder){
        List<Bookorder> orderList = new ArrayList<>();
        Bookorder toReceive;
        orderList = orderRepository.findByBookId(bookorder.getBook().getId());//.setDeliveryState(true);
        for(int i=0; i<orderList.size();++i)
        {
            if(orderList.get(i).getSubmitted()) {
                toReceive = orderRepository.getOne(orderList.get(i).getId());
                toReceive.setDeliveryState(true);
                orderRepository.save(toReceive);
            }
        }
        //order.isDelivered();
        boxRepository.getOne(box.getId()).getBooks().remove(bookorder.getBook());
    }
}

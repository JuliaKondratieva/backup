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
import java.time.LocalDateTime;
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
        System.out.println("START OF ADDBOOK");
        Bookorder recOrder = null;
        List<Bookorder> byBookId = new ArrayList<>();
        List<Bookorder> nextOrderQueue = new ArrayList<>();

        Bookorder nextOrder = null;
        byBookId=orderRepository.findByBookTitle(receivedBook.getTitle());
        for(Bookorder o: byBookId){
            if(o.getSubmitted())
                recOrder=o;
            System.out.println("RECORDER"+o.toString());
        }

        System.out.println("RECORDERAFTERFOR"+ (recOrder==null?"NULL":recOrder.toString()));

        if(recOrder!=null){
            Box toBox = boxRepository.findByAddress(box.getAddress());
            recOrder.getBook().setBoxId(toBox);
            bookRepository.save(recOrder.getBook());

            nextOrderQueue=orderRepository.findByBookTitle(receivedBook.getTitle());
            if(!nextOrderQueue.isEmpty()) {
                System.out.println("nextordernot empty");
                LocalDateTime min = LocalDateTime.now();
                for (Bookorder o : nextOrderQueue) {
                    if (min.isAfter(o.getFromDate())&&o.getId()!=recOrder.getId()) {
                        min = o.getFromDate();
                        nextOrder = o;
                    }
                    System.out.println("O in for"+o.toString());
                }
            }
            System.out.println(recOrder.getDeliveryState());
            System.out.println(recOrder.getSubmitted());
            if(recOrder.getDeliveryState()||!recOrder.getSubmitted()) {
                System.out.println(nextOrder);

                if (nextOrder == null) {
                    recOrder.getBook().setAvailable(true);
                    orderRepository.delete(recOrder);
                } else {
                    orderRepository.delete(recOrder);
                    nextOrder.setSubmitted(true);
                    nextOrder.setFromDate(LocalDateTime.now());
                    orderRepository.save(nextOrder);
                    System.out.println(nextOrder.getUser().getUsername() + ", Your Order was submitted. Expect!");
                }
            }
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
                Book received = toReceive.getBook();
                received.setBoxId(null);
                bookRepository.save(received);
                orderRepository.save(toReceive);
            }
        }
        Box removing = boxRepository.getOne(box.getId());
        removing.getBooks().remove(bookorder.getBook());
        boxRepository.save(removing);
    }
}

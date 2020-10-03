package com.julieandco.bookservice.service;

import com.julieandco.bookservice.entities.Book;
import com.julieandco.bookservice.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @Transactional
    public Book findByTitle(String title){
        return bookRepository.findBookByTitle(title);
    }

    @Transactional
    public void addBook(Book book){
        if(bookRepository.findBookByTitle(book.getTitle()) == null){
            bookRepository.save(book);
        }
    }
}

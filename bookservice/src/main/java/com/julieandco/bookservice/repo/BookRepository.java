package com.julieandco.bookservice.repo;

import com.julieandco.bookservice.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    //@Query("SELECT b FROM Book b WHERE b.title = :title")
    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book findBookByTitle(String title);

   // @Query(value = "SELECT * FROM User")
   // List<Book> findAllBooks();
}

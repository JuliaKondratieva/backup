package com.julieandco.bookservice.repo;

import com.julieandco.bookservice.entities.Bookorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Bookorder, UUID> {
    @Query("SELECT o FROM Bookorder o INNER JOIN Book b ON o.book=b.id WHERE b.id = :id")
    List<Bookorder> findByBookId(@Param("id") UUID id);

    @Query("SELECT o FROM Bookorder o INNER JOIN Book b ON o.book=b.id WHERE b.title = :title")
    List<Bookorder> findByBookTitle(@Param("title") String title);

}

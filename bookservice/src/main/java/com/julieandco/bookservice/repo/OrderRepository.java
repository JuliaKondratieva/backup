package com.julieandco.bookservice.repo;

import com.julieandco.bookservice.entities.Bookorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Bookorder, UUID> {
    //@Query("SELECT o FROM Order o INNER JOIN Book b ON o.book=b.id WHERE b.id = :id")
    List<Bookorder> findByBookId(UUID id);
    //Order getByBook(@Param("id") UUID id);
    //Order findByBookGetSubmitted(boolean bool);

    //@Query("SELECT o FROM Order o INNER JOIN Book b ON o.book=b.id WHERE b.id = :id AND o.fromDate = min(o.fromDate)")
    //Order getNext(@Param("id") UUID id);
    //Order findByBookIdAndMinFromDate(UUID id);
    //@Query("SELECT o FROM Order o WHERE o.id = :id")
    //Order getById(@Param("id") UUID id);
}

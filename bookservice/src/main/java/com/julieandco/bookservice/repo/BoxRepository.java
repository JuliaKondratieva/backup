package com.julieandco.bookservice.repo;

import com.julieandco.bookservice.entities.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BoxRepository extends JpaRepository<Box, UUID> {
    @Query("SELECT b FROM Box b WHERE b.address = :address")
    Box findByAddress(@Param("address") String address);
}

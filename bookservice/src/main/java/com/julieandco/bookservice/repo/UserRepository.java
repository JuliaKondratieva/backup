package com.julieandco.bookservice.repo;
import com.julieandco.bookservice.entities.Book;
import com.julieandco.bookservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    //@Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(String username);
}

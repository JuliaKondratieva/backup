package com.julieandco.bookservice.service;

import com.julieandco.bookservice.entities.User;
import com.julieandco.bookservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void addUser(User user){
        if(userRepository.findByUsername(user.getUsername()) == null){
            userRepository.save(user);
        }
    }
}

package com.julieandco.bookservice.controller;

import com.julieandco.bookservice.entities.User;
import com.julieandco.bookservice.entities.dto.UserDTO;
import com.julieandco.bookservice.entities.dto.UsersDTO;
import com.julieandco.bookservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveUser(@RequestBody UserDTO userDTO){
        String customerUsername = userDTO.getUsername();
        User customer=new User();
        customer.setUsername(customerUsername);
        userService.addUser(customer);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public @ResponseBody
    UsersDTO getAllCustomers(){
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUsers(userService.getAllUsers());
        return usersDTO;
    }
}

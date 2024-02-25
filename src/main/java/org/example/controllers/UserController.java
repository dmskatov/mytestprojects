package org.example.controllers;


import org.example.exceptions.CustomException;
import org.example.models.User;
import org.example.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/api/user")
    public void createUser (@RequestBody User user) throws CustomException {
        userService.createUser(user);
    }

    @PostMapping("/api/buyTicket/{ticketId}")
    public void buyTicket (@PathVariable Integer ticketId) throws CustomException {
        userService.buyTicket(ticketId);
    }



}

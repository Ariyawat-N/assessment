package com.kbtg.bootcamp.posttest.controller;

import com.kbtg.bootcamp.posttest.entity.User;
import com.kbtg.bootcamp.posttest.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listAllTickets() {
        return " Hello ";
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }
}
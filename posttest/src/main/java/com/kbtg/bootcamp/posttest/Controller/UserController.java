package com.kbtg.bootcamp.posttest.Controller;

import com.kbtg.bootcamp.posttest.Repository.UserRepository;
import com.kbtg.bootcamp.posttest.entity.Users;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listAllTickets(){
        return  " Hello ";
    }

    @PostMapping
    public Users createUser(@RequestBody Users users) {
         return  this.userRepository.save(users);
    }
}

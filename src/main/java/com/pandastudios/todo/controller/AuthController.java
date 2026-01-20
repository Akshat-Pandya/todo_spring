package com.pandastudios.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandastudios.todo.entity.User;
import com.pandastudios.todo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

     private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public User registerUser(@RequestBody User user) {
        logger.info("Creating user: username='{}'", user.getUsername());
        User created = userService.createUser(user);
        logger.info("Created user: id={}, username='{}'", created.getId(), created.getUsername());
        return created;
    }

    @GetMapping("/test")
    public String test(){
        return "Test endpoint working";
    }

}

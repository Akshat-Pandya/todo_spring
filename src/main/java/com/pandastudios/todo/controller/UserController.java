package com.pandastudios.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandastudios.todo.entity.User;
import com.pandastudios.todo.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public User registerUser(@RequestBody User user) {
        logger.info("Creating user: username='{}'", user.getUsername());
        User created = userService.createUser(user);
        logger.info("Created user: id={}, username='{}'", created.getId(), created.getUsername());
        return created;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        logger.info("Fetching user by id={}", id);
        User user = userService.getUserById(id);
        logger.debug("Fetched user: id={}, username='{}'", user.getId(), user.getUsername());
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        logger.info("Updating user id={}: username='{}'", id, user.getUsername());
        User updated = userService.updateUser(id, user);
        logger.info("Updated user id={}, username='{}'", updated.getId(), updated.getUsername());
        return updated;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        logger.info("Deleting user id={}", id);
        userService.deleteUser(id);
        logger.info("Deleted user id={}", id);
    }

    @GetMapping("/test")
    public String testconnectioString() {
        return new String("test connection working.");
    }
    
}

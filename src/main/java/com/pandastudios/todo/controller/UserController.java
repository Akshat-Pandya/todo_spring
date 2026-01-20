package com.pandastudios.todo.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandastudios.todo.entity.User;
import com.pandastudios.todo.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User me(Principal principal) {
        log.info("Fetching current user");
        return userService.getByUsername(principal.getName());
    }

    @PutMapping
    public User update(@RequestBody User user, Principal principal) {
        User current = userService.getByUsername(principal.getName());
        log.info("Updating user id={}", current.getId());
        return userService.updateUser(current.getId(), user);
    }

    @DeleteMapping
    public void delete(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        log.warn("Deleting user id={}", user.getId());
        userService.deleteUser(user.getId());
    }
}

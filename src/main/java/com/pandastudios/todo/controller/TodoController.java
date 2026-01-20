package com.pandastudios.todo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandastudios.todo.entity.Todo;
import com.pandastudios.todo.entity.User;
import com.pandastudios.todo.service.TodoService;
import com.pandastudios.todo.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import java.security.Principal;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private static final Logger log = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;
    private final UserService userService;

    public TodoController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        log.info("Creating todo for user={}", user.getUsername());
        return todoService.create(todo, user);
    }

    @GetMapping
    public List<Todo> all(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        return todoService.getAll(user);
    }

    @GetMapping("/{id}")
    public Todo one(@PathVariable Long id, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        return todoService.get(id, user);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody Todo todo, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        log.info("Updating todo id={} for user={}", id, user.getUsername());
        return todoService.update(id, user, todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Principal principal) {
        User user = userService.getByUsername(principal.getName());
        log.warn("Deleting todo id={} for user={}", id, user.getUsername());
        todoService.delete(id, user);
    }
}

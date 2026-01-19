package com.pandastudios.todo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandastudios.todo.entity.Todo;
import com.pandastudios.todo.service.TodoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/{userId}")
    public Todo createTodo(@RequestBody Todo todo, @PathVariable Long userId) {
        return todoService.createTodo(todo, userId);
    }

    @GetMapping("/{userId}")
    public List<Todo> getAll(@PathVariable Long userId) {
        return todoService.getAllTodos(userId);
    }

    @GetMapping("/{userId}/{todoId}")
    public Todo getOne(@PathVariable Long userId, @PathVariable Long todoId) {
        return todoService.getTodoById(todoId, userId);
    }

    @PutMapping("/{userId}/{todoId}")
    public Todo update(
            @PathVariable Long userId,
            @PathVariable Long todoId,
            @RequestBody Todo todo) {
        return todoService.updateTodo(todoId, userId, todo);
    }

    @DeleteMapping("/{userId}/{todoId}")
    public void delete(@PathVariable Long userId, @PathVariable Long todoId) {
        todoService.deleteTodo(todoId, userId);
    }
}

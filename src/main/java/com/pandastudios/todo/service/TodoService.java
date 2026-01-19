package com.pandastudios.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandastudios.todo.entity.Todo;
import com.pandastudios.todo.entity.User;
import com.pandastudios.todo.repository.TodoRepository;
import com.pandastudios.todo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TodoService {

    @Autowired
    private TodoRepository repo;

    @Autowired
    private UserRepository userRepository;

    public Todo createTodo(Todo todo, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        todo.setUser(user);
        return repo.save(todo);
    }

    public List<Todo> getAllTodos(Long userId) {
        return repo.findAllByUserId(userId);
    }

    public Todo getTodoById(Long todoId, Long userId) {
        return repo.findByIdAndUserId(todoId, userId).orElseThrow();
    }

    public Todo updateTodo(Long todoId, Long userId, Todo updated) {
        Todo existing = repo.findByIdAndUserId(todoId, userId).orElseThrow();
        if(updated.getTitle() != null && !updated.getTitle().isEmpty())
            existing.setTitle(updated.getTitle());
        if(updated.getDescription() != null && !updated.getDescription().isEmpty())
            existing.setDescription(updated.getDescription());
        if(updated.getCompleted()!=null)
            existing.setCompleted(updated.getCompleted());
        return repo.save(existing);
    }

    public void deleteTodo(Long todoId, Long userId) {
        Todo todo = repo.findByIdAndUserId(todoId, userId).orElseThrow();
        repo.delete(todo);
    }
}

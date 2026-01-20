package com.pandastudios.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pandastudios.todo.entity.Todo;
import com.pandastudios.todo.entity.User;
import com.pandastudios.todo.exception.ResourceNotFoundException;
import com.pandastudios.todo.repository.TodoRepository;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class TodoService {

    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public Todo create(Todo todo, User user) {
        todo.setUser(user);
        return repo.save(todo);
    }

    public List<Todo> getAll(User user) {
        return repo.findByUser(user);
    }

    public Todo get(Long id, User user) {
        return repo.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));
    }

    public Todo update(Long id, User user, Todo updated) {
        Todo existing = get(id, user);

        if (updated.getTitle() != null) existing.setTitle(updated.getTitle());
        if (updated.getDescription() != null) existing.setDescription(updated.getDescription());
        if (updated.getCompleted() != null) existing.setCompleted(updated.getCompleted());

        return repo.save(existing);
    }

    public void delete(Long id, User user) {
        repo.delete(get(id, user));
    }
}


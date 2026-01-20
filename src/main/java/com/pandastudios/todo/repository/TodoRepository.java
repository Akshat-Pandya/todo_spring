package com.pandastudios.todo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pandastudios.todo.entity.Todo;
import com.pandastudios.todo.entity.User;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUserId(Long userId);

    Optional<Todo> findByIdAndUserId(Long id, Long userId);

    Optional<Todo> findByIdAndUser(Long id, User user);

    List<Todo> findByUser(User user);
}

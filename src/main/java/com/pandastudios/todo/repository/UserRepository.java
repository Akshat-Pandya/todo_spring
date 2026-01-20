package com.pandastudios.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pandastudios.todo.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}

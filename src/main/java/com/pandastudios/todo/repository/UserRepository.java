package com.pandastudios.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pandastudios.todo.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{
    User findByUsername(String username);
    User findByEmail(String email);
}

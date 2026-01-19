package com.pandastudios.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.pandastudios.todo.entity.User;
import com.pandastudios.todo.repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    

    public User createUser(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow();
        if(user.getUsername() != null && !user.getUsername().isEmpty()) 
            existingUser.setUsername(user.getUsername());

        if(user.getEmail() != null && !user.getEmail().isEmpty()) 
            existingUser.setEmail(user.getEmail());

        if(user.getPassword() != null && !user.getPassword().isEmpty()) 
            existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}



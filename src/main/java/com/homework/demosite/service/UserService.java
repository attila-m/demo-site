package com.homework.demosite.service;

import com.homework.demosite.model.User;
import com.homework.demosite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateLastLogin(User user) {
        user.setLastLogin(new Date());
        userRepository.save(user);
    }
}

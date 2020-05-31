package com.kovaliv.lab3.security.services;


import com.kovaliv.lab3.security.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}

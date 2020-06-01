package com.kovaliv.lab3.security.services;


import com.kovaliv.lab3.security.dtos.AddUserRequestDto;
import com.kovaliv.lab3.security.entities.User;

public interface UserService {
    User save(AddUserRequestDto addUserRequestDto);

    User findByUsername(String username);
}

package com.kovaliv.lab3.security.services;


import com.kovaliv.lab3.security.dtos.AddUserRequestDto;
import com.kovaliv.lab3.security.dtos.LoginDto;
import com.kovaliv.lab3.security.dtos.UserDto;
import com.kovaliv.lab3.security.entities.User;

public interface UserService {
    UserDto save(AddUserRequestDto addUserRequestDto);

    User findByUsername(String username);

    UserDto authenticateUser(LoginDto loginDto);
}

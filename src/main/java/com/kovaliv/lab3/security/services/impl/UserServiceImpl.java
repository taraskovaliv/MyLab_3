package com.kovaliv.lab3.security.services.impl;

import com.kovaliv.lab3.constants.ErrorConstants;
import com.kovaliv.lab3.security.entities.User;
import com.kovaliv.lab3.security.repository.RoleRepository;
import com.kovaliv.lab3.security.repository.UserRepository;
import com.kovaliv.lab3.security.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(ErrorConstants.USER_NOT_FOUND_BY_USERNAME + username));
    }
}

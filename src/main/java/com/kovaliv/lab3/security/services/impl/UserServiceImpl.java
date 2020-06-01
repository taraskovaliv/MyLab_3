package com.kovaliv.lab3.security.services.impl;

import com.kovaliv.lab3.constants.ErrorConstants;
import com.kovaliv.lab3.security.dtos.AddUserRequestDto;
import com.kovaliv.lab3.security.entities.Role;
import com.kovaliv.lab3.security.entities.User;
import com.kovaliv.lab3.security.repository.RoleRepository;
import com.kovaliv.lab3.security.repository.UserRepository;
import com.kovaliv.lab3.security.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(AddUserRequestDto addUserRequestDto) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("user"));

        User user = User.builder()
                .password(passwordEncoder.encode(addUserRequestDto.getPassword()))
                .username(addUserRequestDto.getUsername())
                .roles(roles)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(ErrorConstants.USER_NOT_FOUND_BY_USERNAME + username));
    }
}

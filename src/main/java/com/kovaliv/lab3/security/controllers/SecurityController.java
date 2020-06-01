package com.kovaliv.lab3.security.controllers;

import com.kovaliv.lab3.security.dtos.AddUserRequestDto;
import com.kovaliv.lab3.security.dtos.LoginDto;
import com.kovaliv.lab3.security.dtos.UserDto;
import com.kovaliv.lab3.security.jwt.JwtUtils;
import com.kovaliv.lab3.security.services.UserService;
import com.kovaliv.lab3.security.services.impl.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<UserDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(UserDto.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .password(userDetails.getPassword())
                .build());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AddUserRequestDto addUserRequestDto) {
        try {
            userService.findByUsername(addUserRequestDto.getUsername());
        } catch (UsernameNotFoundException e) {
            userService.save(addUserRequestDto);

            return ResponseEntity.ok("User registered successfully!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already registered");
    }
}
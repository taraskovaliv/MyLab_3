package com.kovaliv.lab3.security.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private Long id;

    private String username;

    private String password;

    private String token;
}

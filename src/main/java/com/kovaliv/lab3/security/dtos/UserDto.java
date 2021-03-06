package com.kovaliv.lab3.security.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String username;

    private String password;

    private String token;
}

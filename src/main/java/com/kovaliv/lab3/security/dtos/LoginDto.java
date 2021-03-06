package com.kovaliv.lab3.security.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class LoginDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}

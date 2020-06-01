package com.kovaliv.lab3.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class AddUserToBlackListDto {
    @Min(1)
    private Long userId;
}

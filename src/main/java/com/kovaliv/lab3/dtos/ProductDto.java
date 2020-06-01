package com.kovaliv.lab3.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;
}

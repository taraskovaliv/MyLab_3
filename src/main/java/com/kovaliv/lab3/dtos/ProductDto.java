package com.kovaliv.lab3.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;
}

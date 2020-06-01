package com.kovaliv.lab3.dtos;

import com.kovaliv.lab3.entities.enums.PaidStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaidStatus paidStatus;

    private List<ProductDto> products;
}

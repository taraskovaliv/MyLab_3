package com.kovaliv.lab3.services.impl;

import com.kovaliv.lab3.constants.ErrorConstants;
import com.kovaliv.lab3.dtos.AddOrderDto;
import com.kovaliv.lab3.dtos.OrderDto;
import com.kovaliv.lab3.dtos.PaidOrderDto;
import com.kovaliv.lab3.entities.Order;
import com.kovaliv.lab3.entities.enums.PaidStatus;
import com.kovaliv.lab3.repos.OrderRepo;
import com.kovaliv.lab3.security.services.UserService;
import com.kovaliv.lab3.services.OrderService;
import com.kovaliv.lab3.services.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public List<OrderDto> getAll(String username) {
        return orderRepo.findAll()
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto addOrder(AddOrderDto addOrderDto, String username) {
        Order order = Order.builder()
                .paidStatus(PaidStatus.NOT_PAID)
                .user(userService.findByUsername(username))
                .products(addOrderDto.getProductIds()
                        .stream()
                        .map(productService::findById)
                        .collect(Collectors.toList())
                )
                .build();

        order = orderRepo.save(order);

        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto paidOrder(PaidOrderDto paidOrderDto) {
        Order order = orderRepo.getOne(paidOrderDto.getOrderId());

        paid(paidOrderDto);

        order.setPaidStatus(PaidStatus.PAID);
        order = orderRepo.save(order);

        return modelMapper.map(order, OrderDto.class);
    }

    private void paid(PaidOrderDto paidOrderDto) {
        String cardNumber = paidOrderDto.getCardNumber().toString();
        if (cardNumber.length() != 8) {
            throw new BadCredentialsException(ErrorConstants.CARDNUMBER_NOT_CORRECT);
        }
    }
}

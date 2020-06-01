package com.kovaliv.lab3.controllers;

import com.kovaliv.lab3.dtos.AddOrderDto;
import com.kovaliv.lab3.dtos.OrderDto;
import com.kovaliv.lab3.dtos.PaidOrderDto;
import com.kovaliv.lab3.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/getOrders")
    public ResponseEntity<List<OrderDto>> getOrders(Principal principal) {
        return ResponseEntity.ok(orderService.getAll(principal.getName()));
    }

    @PostMapping("/addOrder")
    public ResponseEntity<OrderDto> addOrder(@RequestBody AddOrderDto addOrderDto, Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(addOrderDto, principal.getName()));
    }

    @PutMapping("/paidOrder")
    public ResponseEntity<OrderDto> paidOrder(@RequestBody PaidOrderDto paidOrderDto) {
        return ResponseEntity.ok(orderService.paidOrder(paidOrderDto));
    }
}

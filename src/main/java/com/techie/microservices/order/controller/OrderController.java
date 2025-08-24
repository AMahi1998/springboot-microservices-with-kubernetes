package com.techie.microservices.order.controller;

import com.techie.microservices.order.dto.OrderRequest;
import com.techie.microservices.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
@Validated
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody @Valid OrderRequest orderRequest){
        log.info("Incoming order: skuCode={}, quantity={}, email={}", orderRequest.skuCode(), orderRequest.quantity(), orderRequest.userDetails()!=null? orderRequest.userDetails().email():null);
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }
}

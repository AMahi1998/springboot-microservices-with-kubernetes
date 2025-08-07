package com.techie.microservices.order.dto;

import java.math.BigDecimal;

public record OrderRequest(Long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity, UserDetails userDetails) {
    // This record class is used to represent the request format for creating an order.
    // It includes fields for id, orderNumber, skuCode, price, quantity, and userDetails.
    public record UserDetails(String email, String firstName, String lastName){}
}

package com.techie.microservices.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record OrderRequest(Long id, String orderNumber, @NotBlank String skuCode, @NotNull @DecimalMin(value = "0.01") BigDecimal price, @NotNull @Min(1) Integer quantity, @Valid @NotNull UserDetails userDetails) {
    // This record class is used to represent the request format for creating an order.
    // It includes fields for id, orderNumber, skuCode, price, quantity, and userDetails.
    public record UserDetails(@Email @NotBlank String email, String firstName, String lastName){}
}

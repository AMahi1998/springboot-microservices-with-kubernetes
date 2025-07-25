package com.techie.microservices.product.dto;

import java.math.BigDecimal;

public record ProductResponse(String id, String name, String description, BigDecimal price) {
    // This record class is used to represent the response format for product details.
    // It includes fields for id, name, description, and price of the product.
}

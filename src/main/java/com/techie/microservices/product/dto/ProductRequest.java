package com.techie.microservices.product.dto;

import java.math.BigDecimal;

public record ProductRequest(String id, String name, String description,BigDecimal price, String skuCode) {
    // This record class is used to represent the request format for creating or updating a product.
    // It includes fields for id, name, description, price, and skuCode.
    // The id field is optional and can be used for updates.
}

package com.example.onlineshopping.dto.cart;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CartCreateRequest {
    private LocalDateTime orderDate;
    private String shippingAddress;
}

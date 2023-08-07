package com.example.onlineshopping.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CartDto {

    private LocalDateTime orderDate;
    private String shippingAddress;

    private String url;
}

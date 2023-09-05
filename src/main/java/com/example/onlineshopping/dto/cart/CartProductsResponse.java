package com.example.onlineshopping.dto.cart;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class CartProductsResponse {
    private String CartProducts;
}

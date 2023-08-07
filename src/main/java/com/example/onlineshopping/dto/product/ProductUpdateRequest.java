package com.example.onlineshopping.dto.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductUpdateRequest {

    private String title;
    private String description;

    private double price;
}

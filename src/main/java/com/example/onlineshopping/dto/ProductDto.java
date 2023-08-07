package com.example.onlineshopping.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    private String title;
    private String description;

    private double price;
}

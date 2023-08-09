package com.example.onlineshopping.dto.product;

import com.example.onlineshopping.dto.PhotoDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCreateRequest {

    private String title;
    private String description;

    private double price;
    private PhotoDto photo;
}

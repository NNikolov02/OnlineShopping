package com.example.onlineshopping.dto.product;

import com.example.onlineshopping.model.Cart;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductResponse {
    private UUID id;

    private String title;
    private String description;

    private double price;


    private String cart;
}

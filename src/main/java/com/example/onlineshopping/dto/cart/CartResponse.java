package com.example.onlineshopping.dto.cart;

import com.example.onlineshopping.dto.CustomerDto;
import com.example.onlineshopping.dto.ProductDto;
import com.example.onlineshopping.model.Customer;
import com.example.onlineshopping.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class CartResponse {
//    private UUID id;
//
//    private LocalDateTime orderDate;
//    private String shippingAddress;
//    private String url;


    private Set<ProductDto> products;


//    private CustomerDto customer;
}

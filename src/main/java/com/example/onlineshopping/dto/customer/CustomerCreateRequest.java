package com.example.onlineshopping.dto.customer;

import com.example.onlineshopping.dto.CartDto;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CustomerCreateRequest {

    private String name;

    private String email;

    private String password;
    //private Set<CartDto> carts;
}

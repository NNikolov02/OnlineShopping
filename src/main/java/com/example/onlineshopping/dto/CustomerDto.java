package com.example.onlineshopping.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

    private String customer_name;

    private String email;

    private String password;
}

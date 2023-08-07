package com.example.onlineshopping.dto.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCreateRequest {

    private String customer_name;

    private String email;

    private String password;
}

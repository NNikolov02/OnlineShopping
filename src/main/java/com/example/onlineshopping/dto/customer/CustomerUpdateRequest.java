package com.example.onlineshopping.dto.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerUpdateRequest {

    private String name;

    private String email;

    private String password;
}

package com.example.onlineshopping.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {


    private String name;

    private String email;

    private String password;

}

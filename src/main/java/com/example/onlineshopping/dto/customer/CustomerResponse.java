package com.example.onlineshopping.dto.customer;

import com.example.onlineshopping.dto.CartDto;
import com.example.onlineshopping.model.Cart;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class CustomerResponse {


    private UUID id;

    private String customer_name;

    private String email;

    private String password;


    private Set<String> carts;


}

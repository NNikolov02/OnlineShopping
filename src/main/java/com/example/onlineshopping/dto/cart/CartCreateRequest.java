package com.example.onlineshopping.dto.cart;

import com.example.onlineshopping.dto.CustomerDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartCreateRequest {

    @JsonProperty("shippingAddress")
    private String shippingAddress;


}

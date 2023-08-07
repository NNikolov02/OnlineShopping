package com.example.onlineshopping.dto.customer;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class CustomerCartResponse {

    private Set<UUID> CustomerCartIds;
}

package com.example.onlineshopping.dto.product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class SetProductRequest {
    @NotNull
    Set<UUID> setProducts;
}

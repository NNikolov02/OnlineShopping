package com.example.onlineshopping.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class SetCartRequest {
    @NotNull
    Set<UUID> setCarts;
}

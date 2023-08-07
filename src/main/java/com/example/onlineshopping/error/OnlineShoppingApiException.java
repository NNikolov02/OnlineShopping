package com.example.onlineshopping.error;

import lombok.Getter;

import java.util.UUID;

@Getter
public class OnlineShoppingApiException extends RuntimeException {

    private final UUID errorId;

    public OnlineShoppingApiException(String message) {
        super(message);
        this.errorId = UUID.randomUUID();
    }
}
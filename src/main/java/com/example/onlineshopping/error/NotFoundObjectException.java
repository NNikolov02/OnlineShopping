package com.example.onlineshopping.error;

import lombok.Getter;

@Getter
public class NotFoundObjectException extends OnlineShoppingApiException{

    private final String objectClass;
    private final Long id;

    public NotFoundObjectException(String message, String objectClass, Long id){
        super(message);
        this.objectClass = objectClass;
        this.id = id;
    }



}

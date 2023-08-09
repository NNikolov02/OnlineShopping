package com.example.onlineshopping.model;

import com.example.onlineshopping.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void clearCart() {
        products.clear();
    }

    // Add other methods as needed, e.g., getTotalPrice, isEmpty, etc.
}

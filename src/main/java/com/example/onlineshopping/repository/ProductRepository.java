package com.example.onlineshopping.repository;

import com.example.onlineshopping.model.Customer;
import com.example.onlineshopping.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByTitle(String title);
    Product findByDescription(String description);

}
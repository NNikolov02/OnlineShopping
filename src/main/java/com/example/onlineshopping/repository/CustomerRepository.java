package com.example.onlineshopping.repository;

import com.example.onlineshopping.model.Cart;
import com.example.onlineshopping.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, UUID> {

    Customer findCustomerByName(String name);
    List<Customer> findAllByName(String name);
    Customer deleteAllByName(String name);

}
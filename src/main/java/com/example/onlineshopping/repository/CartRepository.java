package com.example.onlineshopping.repository;

import com.example.onlineshopping.model.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartRepository extends CrudRepository<Cart, UUID> {
    @Query("SELECT c FROM Cart c JOIN c.customer a WHERE a.name = :customerName")
    List<Cart> findByCustomersName(@Param("customerName") String customerName);
    @Query("SELECT c FROM Cart c JOIN c.customer a WHERE a.name = :customerName")
    Cart findByCustomer_Name(@Param("customerName") String customerName);

}
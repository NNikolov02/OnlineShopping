package com.example.onlineshopping.repository;

import com.example.onlineshopping.model.Cart;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartPagingRepository extends PagingAndSortingRepository<Cart, UUID> {

}

package com.example.onlineshopping.repository;


import com.example.onlineshopping.model.Customer;
import com.example.onlineshopping.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, UUID> {

     Boolean existsByToken(String token);
     VerificationToken findByCustomer(Customer customer);




}

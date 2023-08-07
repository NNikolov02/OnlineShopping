package com.example.onlineshopping.service;


import com.example.onlineshopping.error.NotFoundObjectException;
import com.example.onlineshopping.model.Cart;
import com.example.onlineshopping.model.Product;
import com.example.onlineshopping.repository.ProductPagingRepository;
import com.example.onlineshopping.repository.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private ProductPagingRepository pagingRepo;

    public Page<Product> fetchAll(int currentPage, int pageSize) {
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }

    public Product findById(String productId) {
        return repo.findById(UUID.fromString(productId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Product Not Found", Cart.class.getName(), productId);
        });
    }


    public Product save(Product product) {
        return repo.save(product);
    }
    public void deleteById(String productId){
        repo.deleteById(UUID.fromString(productId));

    }

    public Product findByTitle(String title){
        return repo.findByTitle(title);
    }

    public Product findByDescription(String description){
        return  repo.findByDescription(description);
    }
}

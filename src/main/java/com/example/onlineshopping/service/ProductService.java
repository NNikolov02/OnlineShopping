package com.example.onlineshopping.service;


import com.example.onlineshopping.error.NotFoundObjectException;
import com.example.onlineshopping.model.Cart;
import com.example.onlineshopping.model.Product;
import com.example.onlineshopping.repository.ProductPagingRepository;
import com.example.onlineshopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Component
@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private ProductPagingRepository pagingRepo;

    public List<Product> fetchAll() {
        return (List)repo.findAll();
    }

    public Product findById(Long productId) {
        return repo.findById(productId).orElseThrow(() -> {
            throw new NotFoundObjectException("Product Not Found", Cart.class.getName(), productId);
        });
    }


    public Product save(Product product) {
        return repo.save(product);
    }
    public void deleteById(Long productId){
        repo.deleteById(productId);

    }

    public Product findByTitle(String title){
        return repo.findByTitle(title);
    }

    public Product findByDescription(String description){
        return  repo.findByDescription(description);
    }
}

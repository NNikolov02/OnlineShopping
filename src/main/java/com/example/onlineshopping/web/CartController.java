package com.example.onlineshopping.web;

import com.example.onlineshopping.model.Cart;
import com.example.onlineshopping.model.Product;
import com.example.onlineshopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model) {
        Cart cart = cartService.getOrCreateCart();
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/cart/add")
    public ResponseEntity<String> addToCart(@RequestBody Product product) {
        try {
            cartService.addProductToCart(product);
            return ResponseEntity.ok("Product added to cart.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product to cart.");
        }
    }
}

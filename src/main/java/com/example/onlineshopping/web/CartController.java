package com.example.onlineshopping.web;

import com.example.onlineshopping.dto.cart.CartApiPage;
import com.example.onlineshopping.dto.cart.CartCreateRequest;
import com.example.onlineshopping.dto.cart.CartResponse;
import com.example.onlineshopping.mapping.CartMapper;
import com.example.onlineshopping.model.Cart;
import com.example.onlineshopping.service.CartService;
import com.example.onlineshopping.validation.ObjectValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onlineStore/carts")
@AllArgsConstructor
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ObjectValidator validator;




    @GetMapping(name = "", produces = "application/json")
    public CartApiPage<CartResponse> getAllCarts(
            @RequestParam(required = false, defaultValue = "1") Integer currPage) {


        Page<CartResponse> cartPage = cartService.fetchAll(currPage - 1, 10).map(cartMapper::responseFromModelOne);

        for (CartResponse response : cartPage){
            response.setUrl("http://localhost:8086/swapi/films/" + response.getId());
        }

        return new CartApiPage<>(cartPage);

    }

    @GetMapping(value ="customer/{customerName}")
    public ResponseEntity<List<CartResponse>> findByCustomer(@PathVariable String customerName ){

        List<Cart> carts = cartService.findByCustomer(customerName);

        return ResponseEntity.ok(cartMapper.responseFromModelList(carts));


    }

    @GetMapping(value ="/{cartId}")
    public ResponseEntity<CartResponse> findById(@PathVariable String cartId){

        Cart cart = cartService.findById(cartId);

        return ResponseEntity.ok(cartMapper.responseFromModelOne(cart));
    }

    @DeleteMapping(value ="/{cartId}")
    public void deleteById(@PathVariable String cartId){
        cartService.deleteById(cartId);
    }

    @PostMapping("")
    public ResponseEntity<CartResponse>createCart(@RequestBody CartCreateRequest cartDto){
        Cart create = cartMapper.modelFromCreateRequest(cartDto);

        Cart saved = cartService.save(create);

        CartResponse cartResponse = cartMapper.responseFromModelOne(saved);

        return ResponseEntity.status(201).body(cartResponse);
    }

}

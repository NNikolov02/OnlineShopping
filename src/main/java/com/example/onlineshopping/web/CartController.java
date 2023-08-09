package com.example.onlineshopping.web;

import com.example.onlineshopping.dto.cart.*;
import com.example.onlineshopping.dto.product.SetProductRequest;
import com.example.onlineshopping.error.InvalidObjectException;
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
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
            response.setUrl("http://localhost:8086/onlieStore/carts/" + response.getId());
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
        CartResponse cartResponse = cartMapper.responseFromModelOne(cart);
        cartResponse.setUrl("http://localhost:8086/onlineStore/carts/" + cartResponse.getId());

        return ResponseEntity.ok().body(cartResponse);
    }

    @DeleteMapping(value ="/{cartId}")
    public void deleteById(@PathVariable String cartId){
        cartService.deleteById(cartId);
    }

    @PostMapping("")
    public ResponseEntity<CartResponse>createCart(@RequestBody CartCreateRequest cartDto){

        Map<String, String> validationErrors = validator.validate(cartDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Cart Create", validationErrors);
        }
        Cart create = cartMapper.modelFromCreateRequest(cartDto);

        Cart saved = cartService.save(create);

        CartResponse cartResponse = cartMapper.responseFromModelOne(saved);

        return ResponseEntity.status(201).body(cartResponse);
    }

    @PatchMapping(value ="/{cartId}")
    public ResponseEntity<CartResponse>updateCart(@PathVariable String cartId, @RequestBody CartUpdateRequest cartDto){
        Map<String, String> validationErrors = validator.validate(cartDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Cart Update", validationErrors);
        }

        Cart findCart = cartService.findById(cartId);
        cartMapper.updateModelFromDto(cartDto, findCart);

        Cart saveCart = cartService.save(findCart);

        CartResponse cartResponse = cartMapper.responseFromModelOne(saveCart);


        return ResponseEntity.status(202).body(cartResponse);




    }


    @PutMapping(value = "/{cartId}/products")
    public CartProductsResponse setAllCartProducts(@PathVariable String cartId, @RequestBody SetProductRequest products) {

        Set<UUID> cartsProducts = cartService.setCartProducts(cartId,products.getSetProducts());

        CartProductsResponse result =  CartProductsResponse.builder().CartProductsIds(cartsProducts).build();

        return result;
    }

}

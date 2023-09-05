package com.example.onlineshopping.service;

import com.example.onlineshopping.dto.cart.CartResponse;
import com.example.onlineshopping.error.NotFoundObjectException;
import com.example.onlineshopping.model.Cart;
import com.example.onlineshopping.model.Customer;
import com.example.onlineshopping.model.Product;
import com.example.onlineshopping.repository.CartPagingRepository;
import com.example.onlineshopping.repository.CartRepository;
import com.example.onlineshopping.repository.CustomerRepository;
import com.example.onlineshopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@Service
public class CartService {

    @Autowired
    private CartRepository repo;

    @Autowired
    private CartPagingRepository pagingRepo;

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CustomerRepository customerRepo;

    public Page<Cart> fetchAll(int currentPage, int pageSize) {
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }

    public Cart findById(String cartId) {
        return repo.findById(UUID.fromString(cartId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Cart Not Found", Cart.class.getName(), cartId);
        });
    }


    public Cart save(Cart cart) {
        return repo.save(cart);
    }
    public void deleteById(String cartId){
        repo.deleteById(UUID.fromString(cartId));

    }

    public List <Cart>findByCustomer(String customerName){
        return repo.findByCustomersName(customerName);
    }

    public String setCartProducts(String name, String title) {
        Cart cart = repo.findByCustomer_Name(name);



        List<Product> allCartProducts =
                (List<Product>) productRepo.findAllByTitle(title);

        cart.setProducts(new HashSet<>(allCartProducts));
        for(Product product:allCartProducts) {
            cart.setTotalPrice(0.0);
            cart.setTotalPrice(cart.getTotalPrice() + product.getPrice());
        }
        Cart savedCart = repo.save(cart);

        //Set<UUID> allCartProductsIds = new HashSet<>();
        //for (Product product : savedCart.getProducts()) {
           // allCartProductsIds.add(product.getId());
       // }

        return "The product is chosen!";
    }

}

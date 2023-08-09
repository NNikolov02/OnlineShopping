//package com.example.onlineshopping.service;
//
//import com.example.onlineshopping.error.NotFoundObjectException;
//import com.example.onlineshopping.model.Cart;
//import com.example.onlineshopping.model.Customer;
//import com.example.onlineshopping.model.Product;
//import com.example.onlineshopping.repository.CartRepository;
//import com.example.onlineshopping.repository.CustomerPagingRepository;
//import com.example.onlineshopping.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.UUID;
//
//@Component
//@Service
//public class CustomerService {
//
//    @Autowired
//    private CustomerRepository repo;
//
//    @Autowired
//    private CustomerPagingRepository pagingRepo;
//
//    @Autowired
//    private CartRepository cartRepo;
//
//    public Page<Customer> fetchAll(int currentPage, int pageSize) {
//        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
//    }
//
//    public Customer findById(String customerId) {
//        return repo.findById(UUID.fromString(customerId)).orElseThrow(() -> {
//            throw new NotFoundObjectException("Customer Not Found", Cart.class.getName(),  customerId);
//        });
//    }
//
//    public void deleteById(String customerId){
//        repo.deleteById(UUID.fromString(customerId));
//
//    }
//
//    public Customer save(Customer customer){
//        return repo.save(customer);
//    }
//
//    public Set<UUID> setCustomerCarts(String customerId, Set<UUID> customerCartsIds) {
//        Customer customer = repo.findById(UUID.fromString(customerId)).orElseThrow(() -> {
//            throw new NotFoundObjectException("Customer Not Found", Cart.class.getName(),customerId);
//        });
//
//        List<Cart> allCustomerCarts =
//                (List<Cart>) cartRepo.findAllById(customerCartsIds);
//
//        customer.setCarts(new HashSet<>(allCustomerCarts));
//        Customer savedCustomer = repo.save(customer);
//
//        Set<UUID> allCustomerCartsIds = new HashSet<>();
//        for (Cart cart : savedCustomer.getCarts()) {
//            allCustomerCartsIds.add(cart.getId());
//        }
//
//        return allCustomerCartsIds;
//    }
//}

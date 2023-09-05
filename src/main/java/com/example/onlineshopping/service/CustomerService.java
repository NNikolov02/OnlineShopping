package com.example.onlineshopping.service;

import com.example.onlineshopping.dto.customer.CustomerCreateRequest;
import com.example.onlineshopping.error.InvalidObjectException;
import com.example.onlineshopping.error.NotFoundObjectException;
import com.example.onlineshopping.model.Cart;
import com.example.onlineshopping.model.Customer;
import com.example.onlineshopping.model.Product;
import com.example.onlineshopping.model.VerificationToken;
import com.example.onlineshopping.repository.CartRepository;
import com.example.onlineshopping.repository.CustomerPagingRepository;
import com.example.onlineshopping.repository.CustomerRepository;
import com.example.onlineshopping.repository.VerificationTokenRepository;
import com.example.onlineshopping.validation.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Component
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private CustomerPagingRepository pagingRepo;

    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private ObjectValidator validator;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VerificationTokenRepository verificationTokenRepo;

    public Page<Customer> fetchAll(int currentPage, int pageSize) {
        return pagingRepo.findAll(PageRequest.of(currentPage, pageSize));
    }

    public Customer findById(String customerId) {
        return repo.findById(UUID.fromString(customerId)).orElseThrow(() -> {
            throw new NotFoundObjectException("Customer Not Found", Cart.class.getName(),  customerId);
        });
    }
    public Customer findByName(String name){

        return repo.findCustomerByName(name);
    }
    public void deleteByName(String name){
        repo.deleteAllByName(name);
    }

    public void deleteById(String customerId){
        repo.deleteById(UUID.fromString(customerId));

    }

    public Customer save(Customer customer){
        return repo.save(customer);
    }

    //public Set<UUID> setCustomerCarts(String customerId, Set<UUID> customerCartsIds) {
       // Customer customer = repo.findById(UUID.fromString(customerId)).orElseThrow(() -> {
            //throw new NotFoundObjectException("Customer Not Found", Cart.class.getName(),customerId);
       // });

        //List<Cart> allCustomerCarts =
                //(List<Cart>) cartRepo.findAllById(customerCartsIds);

        //customer.setCarts(new HashSet<>(allCustomerCarts));
        //Customer savedCustomer = repo.save(customer);

        //Set<UUID> allCustomerCartsIds = new HashSet<>();
        //for (Cart cart : savedCustomer.getCarts()) {
            //allCustomerCartsIds.add(cart.getId());
        //}

        //return allCustomerCartsIds;
    //}
    public Customer registerNewCustomerAccount(CustomerCreateRequest customerDto)  {
        Map<String, String> validationErrors = validator.validate(customerDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Customer Create", validationErrors);
        }


        Customer customer = Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .build();

        Customer savedCustomer = repo.save(customer);

        // Create a verification token and associate it with the customer
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .customer(savedCustomer)
                .expiryDate(calculateExpiryDate(24 * 60)) // 24 hours expiration
                .build();
        String confirmationUrl =  "http://localhost:8084/library/customers/registration-success?" + verificationToken;
        String message = "Thank you for registrating. Please click on the link below to see your personal data:\n"
               + confirmationUrl;
        emailService.sendSimpleMessage(customerDto.getEmail(),"Email Verification",message);

        verificationTokenRepo.save(verificationToken);

        return savedCustomer;
    }
    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
    public void createVerificationToken(Customer customer, String token) {
        System.out.println("Creating verification token for customer: " + customer.getName());
        System.out.println("Token: " + token);
    }
}

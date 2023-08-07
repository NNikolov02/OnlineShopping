package com.example.onlineshopping.web;

import com.example.onlineshopping.dto.cart.CartProductsResponse;
import com.example.onlineshopping.dto.cart.SetCartRequest;
import com.example.onlineshopping.dto.customer.*;
import com.example.onlineshopping.dto.product.*;
import com.example.onlineshopping.error.InvalidObjectException;
import com.example.onlineshopping.mapping.CustomerMapper;
import com.example.onlineshopping.model.Customer;
import com.example.onlineshopping.model.Product;
import com.example.onlineshopping.service.CustomerService;
import com.example.onlineshopping.validation.ObjectValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/onlineStore/customers")
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ObjectValidator validator;

    private final Integer Page_Size = 10;


    @GetMapping(name = "", produces = "application/json")
    public CustomerApiPage<CustomerResponse> getAllCustomers(
            @RequestParam(required = false, defaultValue = "0") Integer currPage) {


        Page<CustomerResponse> customersPage =customerService.fetchAll(currPage, Page_Size)
                .map(customerMapper::responseFromModel);

        return new CustomerApiPage<>(customersPage);
    }

    @GetMapping(value ="/{customerId}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable String customerId){

        Customer customer = customerService.findById(customerId);


        return ResponseEntity.ok(customerMapper.responseFromModel(customer));
    }



    @DeleteMapping(value ="/{customerId}")
    public void deleteById(@PathVariable String customerId){
        customerService.deleteById(customerId);
    }

    @PostMapping("")
    public ResponseEntity<CustomerResponse>createProduct(@RequestBody CustomerCreateRequest customerDto){

        Map<String, String> validationErrors = validator.validate(customerDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Customer Create", validationErrors);
        }

        Customer createCustomer = customerMapper.modelFromCreateRequest(customerDto);

        Customer savedCustomer = customerService.save(createCustomer);

        CustomerResponse customerResponse = customerMapper.responseFromModel(savedCustomer);


        return  ResponseEntity.status(201).body(customerResponse);
    }

    @PatchMapping(value ="/{customerId}")
    public ResponseEntity<CustomerResponse>updateProduct(@PathVariable String customerId, @RequestBody CustomerUpdateRequest customerDto){
        Map<String, String> validationErrors = validator.validate(customerDto);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Customer Update", validationErrors);
        }

        Customer findCustomer = customerService.findById(customerId);
        customerMapper.updateModelFromDto(customerDto, findCustomer);

        Customer savedCustomer= customerService.save(findCustomer);

        CustomerResponse customerResponse = customerMapper.responseFromModel(savedCustomer);


        return ResponseEntity.status(202).body(customerResponse);




    }

    @PutMapping(value = "/{customerId}/carts")
    public CustomerCartResponse setAllCustomerCarts(@PathVariable String customerId, @RequestBody SetCartRequest carts) {

        Set<UUID> customerCarts = customerService.setCustomerCarts(customerId,carts.getSetCarts());

        CustomerCartResponse result =  CustomerCartResponse.builder().CustomerCartIds(customerCarts).build();

        return result;
    }
}

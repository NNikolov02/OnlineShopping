package com.example.onlineshopping.mapping;

import com.example.onlineshopping.dto.cart.CartCreateRequest;
import com.example.onlineshopping.dto.cart.CartResponse;
import com.example.onlineshopping.dto.cart.CartUpdateRequest;
import com.example.onlineshopping.dto.customer.CustomerCreateRequest;
import com.example.onlineshopping.dto.customer.CustomerResponse;
import com.example.onlineshopping.dto.customer.CustomerUpdateRequest;
import com.example.onlineshopping.model.Cart;
import com.example.onlineshopping.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Mapper
public interface CustomerMapper {
    Customer modelFromCreateRequest(CustomerCreateRequest customerCreateDto);

    CustomerResponse responseFromModel(Customer customer);

    @Mapping(target = "name",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto(CustomerUpdateRequest customerUpdateDto, @MappingTarget Customer customer);

    public static Set<String> cartUrlsFromCustomer(Set<Cart> carts){
        Set<String> cartUrls = new HashSet<>();

        if(carts != null){
            for(Cart cart : carts){
                cartUrls.add("http://localhost:8086/onlineStore/carts/" + cart.getId());
            }
        }

        return cartUrls;
    }
}

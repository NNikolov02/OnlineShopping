package com.example.onlineshopping.mapping;

import com.example.onlineshopping.dto.customer.CustomerCreateRequest;
import com.example.onlineshopping.dto.customer.CustomerResponse;
import com.example.onlineshopping.dto.customer.CustomerUpdateRequest;
import com.example.onlineshopping.dto.product.ProductCreateRequest;
import com.example.onlineshopping.dto.product.ProductResponse;
import com.example.onlineshopping.dto.product.ProductUpdateRequest;
import com.example.onlineshopping.model.Cart;
import com.example.onlineshopping.model.Customer;
import com.example.onlineshopping.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Mapper
public interface ProductMapper {

    Product modelFromCreateRequest(ProductCreateRequest productCreateDto);

    ProductResponse responseFromModel(Product product);

    @Mapping(target = "title",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "price", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto(ProductUpdateRequest productUpdateDto, @MappingTarget Product product);

    public static String cartUrlFromProducts(Cart cart){


        if(cart != null){

            return "http://localhost:8080/store/carts/" + cart.getId();

        }

        return null;
    }
}

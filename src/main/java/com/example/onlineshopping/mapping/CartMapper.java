package com.example.onlineshopping.mapping;

import com.example.onlineshopping.dto.cart.CartCreateRequest;
import com.example.onlineshopping.dto.cart.CartResponse;
import com.example.onlineshopping.dto.cart.CartUpdateRequest;
import com.example.onlineshopping.model.Cart;
import com.example.onlineshopping.model.Customer;
import com.example.onlineshopping.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = {ProductMapperDto.class, CustomerMapperDto.class})
public interface CartMapper {

    Cart modelFromCreateRequest(CartCreateRequest cartCreateDto);

    CartResponse responseFromModelOne(Cart cart);
    List< CartResponse> responseFromModelList(List<Cart> carts);

    @Mapping(target = "orderDate",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "shippingAddress", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDto(CartUpdateRequest cartUpdateDto, @MappingTarget Cart cart);

    public static String customersUrlFromCustomer(Customer customer) {


        if (customer != null) {

            return "http://localhost:8086/onlineStore/customers/" + customer.getName();

        }

        return null;
    }
    public static String productsUrlFromProduct(Product product) {


        if (product != null) {

            return "http://localhost:8086/onlineStore/products/" + product.getTitle();

        }

        return null;
    }
}

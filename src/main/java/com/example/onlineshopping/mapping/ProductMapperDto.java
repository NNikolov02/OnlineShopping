package com.example.onlineshopping.mapping;

import com.example.onlineshopping.dto.CustomerDto;
import com.example.onlineshopping.dto.ProductDto;
import com.example.onlineshopping.model.Customer;
import com.example.onlineshopping.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapperDto {
    ProductDto modelRoDto(Product product);

    Product dtoModel(Product productDto);
}

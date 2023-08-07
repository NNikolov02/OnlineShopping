package com.example.onlineshopping.mapping;

import com.example.onlineshopping.dto.CustomerDto;
import com.example.onlineshopping.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapperDto {
    CustomerDto modelRoDto(Customer customer);

    Customer dtoModel(Customer customerDto);
}

package com.example.demo.mapper;

import com.example.demo.dto.*;
import com.example.demo.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDTO dto) {

        Customer c = new Customer();
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setPhoneNumber(dto.getPhoneNumber());
        c.setAddress(dto.getAddress());

        return c;
    }

    public static CustomerResponseDTO toDTO(Customer customer) {

        CustomerResponseDTO dto = new CustomerResponseDTO();

        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setAddress(customer.getAddress());

        return dto;
    }
}
package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.*;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerResponseDTO createCustomer(@RequestBody CustomerRequestDTO dto) {
        return service.createCustomer(dto);
    }

    @GetMapping
    public List<CustomerResponseDTO> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable Long id) {
        return service.getCustomerById(id);
    }
}
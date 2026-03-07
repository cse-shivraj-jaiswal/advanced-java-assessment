package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.*;
import com.example.demo.service.PolicyService;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private PolicyService service;

    public PolicyController(PolicyService service) {
        this.service = service;
    }

    @PostMapping
    public PolicyResponseDTO createPolicy(@RequestBody PolicyRequestDTO dto) {
        return service.createPolicy(dto);
    }

    @GetMapping("/{id}")
    public PolicyResponseDTO getPolicy(@PathVariable Long id) {
        return service.getPolicyById(id);
    }

    @DeleteMapping("/{id}")
    public void cancelPolicy(@PathVariable Long id) {
        service.cancelPolicy(id);
    }

    @GetMapping("/type/{type}")
    public List<PolicyResponseDTO> getByType(@PathVariable String type) {
        return service.getPoliciesByType(type);
    }

    @GetMapping("/premium")
    public List<PolicyResponseDTO> getByPremium(@RequestParam double min, @RequestParam double max) {
        return service.getPoliciesByPremiumRange(min, max);
    }

    @GetMapping
    public Page<PolicyResponseDTO> getPolicies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return service.getPolicies(page, size, sortBy, direction);
    }
}
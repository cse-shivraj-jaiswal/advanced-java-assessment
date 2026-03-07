package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PolicyRequestDTO;
import com.example.demo.dto.PolicyResponseDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Policy;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.PolicyNotFoundException;
import com.example.demo.mapper.PolicyMapper;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PolicyRepository;
import com.example.demo.service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {

    private PolicyRepository policyRepository;
    private CustomerRepository customerRepository;

    public PolicyServiceImpl(PolicyRepository policyRepository,
                             CustomerRepository customerRepository) {
        this.policyRepository = policyRepository;
        this.customerRepository = customerRepository;
    }

    // CREATE POLICY
    @Override
    public PolicyResponseDTO createPolicy(PolicyRequestDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found with id " + dto.getCustomerId()));

        Policy policy = new Policy();

        policy.setPolicyNumber(dto.getPolicyNumber());
        policy.setPolicyType(dto.getPolicyType());
        policy.setPremiumAmount(dto.getPremiumAmount());
        policy.setCoverageAmount(dto.getCoverageAmount());
        policy.setStartDate(dto.getStartDate());
        policy.setEndDate(dto.getEndDate());
        policy.setStatus("ACTIVE");
        policy.setCustomer(customer);

        Policy savedPolicy = policyRepository.save(policy);

        return PolicyMapper.toDTO(savedPolicy);
    }

    // GET POLICY BY ID
    @Override
    public PolicyResponseDTO getPolicyById(Long id) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found with id " + id));

        return PolicyMapper.toDTO(policy);
    }

    // CANCEL POLICY
    @Override
    public void cancelPolicy(Long id) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found with id " + id));

        policy.setStatus("CANCELLED");

        policyRepository.save(policy);
    }

    // FILTER BY TYPE
    @Override
    public List<PolicyResponseDTO> getPoliciesByType(String type) {

        return policyRepository.findByPolicyType(type)
                .stream()
                .map(PolicyMapper::toDTO)
                .collect(Collectors.toList());
    }

    // FILTER BY PREMIUM RANGE
    @Override
    public List<PolicyResponseDTO> getPoliciesByPremiumRange(double min, double max) {

        return policyRepository.findByPremiumAmountBetween(min, max)
                .stream()
                .map(PolicyMapper::toDTO)
                .collect(Collectors.toList());
    }

    // PAGINATION + SORTING
    @Override
    public Page<PolicyResponseDTO> getPolicies(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Policy> policyPage = policyRepository.findAll(pageable);

        return policyPage.map(PolicyMapper::toDTO);
    }
}
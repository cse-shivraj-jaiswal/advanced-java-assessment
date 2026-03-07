package com.example.demo.service;

import java.util.List;
import org.springframework.data.domain.Page;

import com.example.demo.dto.*;

public interface PolicyService {

    PolicyResponseDTO createPolicy(PolicyRequestDTO dto);

    PolicyResponseDTO getPolicyById(Long id);

    void cancelPolicy(Long id);

    List<PolicyResponseDTO> getPoliciesByType(String type);

    List<PolicyResponseDTO> getPoliciesByPremiumRange(double min, double max);

    Page<PolicyResponseDTO> getPolicies(int page, int size, String sortBy, String direction);
}
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    List<Policy> findByPolicyType(String policyType);

    List<Policy> findByCustomerId(Long customerId);

    List<Policy> findByPremiumAmountBetween(double min, double max);

    @Query("SELECT p FROM Policy p WHERE p.customer.email = :email")
    List<Policy> findPoliciesByCustomerEmail(@Param("email") String email);
}
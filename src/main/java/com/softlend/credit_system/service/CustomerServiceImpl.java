package com.softlend.credit_system.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softlend.credit_system.dto.CreditGapResponse;
import com.softlend.credit_system.dto.CreditProfileResponse;
import com.softlend.credit_system.dto.CustomerRequest;
import com.softlend.credit_system.dto.CustomerResponse;
import com.softlend.credit_system.dto.ImprovementSummaryResponse;
import com.softlend.credit_system.entity.CreditGap;
import com.softlend.credit_system.entity.Customer;
import com.softlend.credit_system.enums.Gapstatus;
import com.softlend.credit_system.exception.CustomerNotFoundException;
import com.softlend.credit_system.repository.CreditGapRepository;
import com.softlend.credit_system.repository.CustomerRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	 private final CustomerRepository repository;

	 @Override
	public CustomerResponse createCustomer(CustomerRequest request) {
		 Customer customer = new Customer();

	        customer.setName(request.getName());
	        customer.setCibil_score(request.getCibilScore());
	        customer.setScoreFetchedAt(LocalDateTime.now());

	        Customer saved = repository.save(customer);

	        return CustomerResponse.builder()
	                .id(saved.getId())
	                .name(saved.getName())
	                .cibilScore(saved.getCibil_score())
	                .scoreFetchedAt(saved.getScoreFetchedAt())
	                .build();
	}
	 
	 @Override
	 public List<CustomerResponse> getAllCustomers() {

	     List<Customer> customers = repository.findAll();

	     return customers.stream()
	             .map(customer -> {
	                 CustomerResponse response = new CustomerResponse();

	                 response.setId(customer.getId());
	                 response.setName(customer.getName());
	                 response.setCibilScore(customer.getCibil_score());
	                 response.setScoreFetchedAt(customer.getScoreFetchedAt());

	                 return response;
	             })
	             .collect(Collectors.toList());
	 }

	 @Override
	 public CustomerResponse getCustomerById(Long id) {
		 Customer customer = repository.findById(id)
		            .orElseThrow(() ->
		                    new CustomerNotFoundException(
		                            "Customer not found"));

		    return CustomerResponse.builder()
		            .id(customer.getId())
		            .name(customer.getName())
		            .cibilScore(customer.getCibil_score())
		            .scoreFetchedAt(customer.getScoreFetchedAt())
		            .build();
	 }
	 
	 @Autowired
	 private CreditGapRepository creditGapRepository;

	 @Override
	 public CreditProfileResponse getCreditProfile(Long customerId) {
		 Customer customer = repository.findById(customerId)
		            .orElseThrow(() ->
		                    new CustomerNotFoundException(
		                            "Customer not found"));

		    List<CreditGap> gaps =
		            creditGapRepository.findByCustomerId(customerId);

		    int gain = gaps.stream()
		            .filter(g ->
		                    g.getStatus() == Gapstatus.OPEN)
		            .mapToInt(
		                    CreditGap::getEstimatedScoreGain)
		            .sum();

		    long openGaps = gaps.stream()
		            .filter(g ->
		                    g.getStatus() == Gapstatus.OPEN)
		            .count();

		    long resolvedGaps = gaps.stream()
		            .filter(g ->
		                    g.getStatus() == Gapstatus.RESOLVED)
		            .count();

		    List<CreditGapResponse> gapResponses =
		            gaps.stream()
		            .map(g -> CreditGapResponse.builder()
		                    .id(g.getId())
		                    .factor(g.getFactor())
		                    .impact(g.getImpact())
		                    .estimatedScoreGain(
		                            g.getEstimatedScoreGain())
		                    .actionDescription(
		                            g.getActionDescription())
		                    .status(
		                            g.getStatus().name())
		                    .build())
		            .toList();

		    return CreditProfileResponse.builder()
		            .customerId(customer.getId())
		            .name(customer.getName())
		            .cibilScore(customer.getCibil_score())
		            .scoreFetchedAt(
		                    customer.getScoreFetchedAt())
		            .potentialScore(
		                    customer.getCibil_score()
		                    + gain)
		            .gaps(gapResponses)
		            .openGaps(openGaps)
		            .resolvedGaps(resolvedGaps)
		            .build();
	 }

	 @Override
	 public ImprovementSummaryResponse getImprovementSummary(Long customerId) {
		 Customer customer = repository.findById(customerId)
		            .orElseThrow(() ->
		                    new CustomerNotFoundException(
		                            "Customer not found"));

		    List<CreditGap> gaps =
		            creditGapRepository.findByCustomerId(customerId);

		    long resolvedGaps = gaps.stream()
		            .filter(g ->
		                    g.getStatus() == Gapstatus.RESOLVED)
		            .count();

		    int totalRecoveredPoints = gaps.stream()
		            .filter(g ->
		                    g.getStatus() == Gapstatus.RESOLVED)
		            .mapToInt(
		                    CreditGap::getEstimatedScoreGain)
		            .sum();

		    int remainingPotentialGain = gaps.stream()
		            .filter(g ->
		                    g.getStatus() == Gapstatus.OPEN)
		            .mapToInt(
		                    CreditGap::getEstimatedScoreGain)
		            .sum();

		    return ImprovementSummaryResponse.builder()
		            .customerId(customer.getId())
		            .resolvedGaps(resolvedGaps)
		            .totalRecoveredPoints(totalRecoveredPoints)
		            .remainingPotentialGain(
		                    remainingPotentialGain)
		            .build();
	 }
}

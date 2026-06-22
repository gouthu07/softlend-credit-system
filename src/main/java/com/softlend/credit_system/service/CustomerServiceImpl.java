package com.softlend.credit_system.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import com.softlend.credit_system.dto.CustomerRequest;
import com.softlend.credit_system.dto.CustomerResponse;
import com.softlend.credit_system.entity.Customer;
import com.softlend.credit_system.exception.CustomerNotFoundException;
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
}

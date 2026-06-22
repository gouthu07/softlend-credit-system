package com.softlend.credit_system.service;

import java.util.List;

import com.softlend.credit_system.dto.CustomerRequest;
import com.softlend.credit_system.dto.CustomerResponse;

public interface CustomerService {
	CustomerResponse createCustomer(CustomerRequest request);

	List<CustomerResponse> getAllCustomers();
	CustomerResponse getCustomerById(Long id);
}



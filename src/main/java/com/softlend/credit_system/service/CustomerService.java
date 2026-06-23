package com.softlend.credit_system.service;

import java.util.List;

import com.softlend.credit_system.dto.CreditProfileResponse;
import com.softlend.credit_system.dto.CustomerRequest;
import com.softlend.credit_system.dto.CustomerResponse;
import com.softlend.credit_system.dto.ImprovementSummaryResponse;

public interface CustomerService {
	CustomerResponse createCustomer(CustomerRequest request);

	List<CustomerResponse> getAllCustomers();
	CustomerResponse getCustomerById(Long id);
	
	CreditProfileResponse getCreditProfile(Long customerId);
	
	ImprovementSummaryResponse
	getImprovementSummary(Long customerId);
}



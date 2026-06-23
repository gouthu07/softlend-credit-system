package com.softlend.credit_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softlend.credit_system.dto.CreditProfileResponse;
import com.softlend.credit_system.dto.CustomerRequest;
import com.softlend.credit_system.dto.CustomerResponse;
import com.softlend.credit_system.dto.ImprovementSummaryResponse;
import com.softlend.credit_system.service.CustomerService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
	 private final CustomerService service;

	    @PostMapping
	    public ResponseEntity<CustomerResponse> createCustomer(
	            @RequestBody CustomerRequest request) {

	        return new ResponseEntity<>(
	                service.createCustomer(request),
	                HttpStatus.CREATED);
}
	    
	    @GetMapping
	    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {

	        return ResponseEntity.ok(service.getAllCustomers());
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<CustomerResponse> getCustomerById(
	            @PathVariable Long id) {

	        return ResponseEntity.ok(
	                service.getCustomerById(id));
	    }
	    
	    @GetMapping("/{id}/credit-profile")
	    public ResponseEntity<CreditProfileResponse>
	    getCreditProfile(@PathVariable Long id) {

	        return ResponseEntity.ok(
	                service.getCreditProfile(id));
	    }
	    
	    @GetMapping("/{id}/improvement-summary")
	    public ResponseEntity<ImprovementSummaryResponse>
	    getImprovementSummary(
	            @PathVariable Long id) {

	        return ResponseEntity.ok(
	                service.getImprovementSummary(id));
	    }
}

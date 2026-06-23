package com.softlend.credit_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softlend.credit_system.dto.EmiResponse;
import com.softlend.credit_system.dto.OfferListResponse;
import com.softlend.credit_system.dto.OfferRequest;
import com.softlend.credit_system.dto.OfferResponse;
import com.softlend.credit_system.dto.OfferStatusRequest;
import com.softlend.credit_system.service.EmiResponseService;
import com.softlend.credit_system.service.OfferService;

@RestController
@RequestMapping("/customers")
public class OfferController {
	 @Autowired
	    private OfferService service;
	 
	 @Autowired
	    private EmiResponseService eservice;

	    @PostMapping("/{id}/offers")
	    public ResponseEntity<OfferResponse> createOffer(
	            @PathVariable Long id,
	            @RequestBody OfferRequest request) {

	        return new ResponseEntity<>(
	                service.createOffer(id, request),
	                HttpStatus.CREATED);
	    }
	    
	    @GetMapping("/{id}/offers")
	    public ResponseEntity<List<OfferListResponse>> getOffers(
	            @PathVariable Long id) {

	        return ResponseEntity.ok(
	                service.getOffers(id));
	    }
	    
	    @PatchMapping("/offers/{id}/status")
	    public ResponseEntity<OfferResponse>
	    updateStatus(
	            @PathVariable Long id,
	            @RequestBody OfferStatusRequest request) {

	        return ResponseEntity.ok(
	                service.updateStatus(id, request));
	    }
	    
	    @GetMapping("/offers/{id}/emi")
	    public ResponseEntity<EmiResponse>
	    calculateEmi(
	            @PathVariable Long id) {

	        return ResponseEntity.ok(
	                eservice.calculateEmi(id));
	    }
}

package com.softlend.credit_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softlend.credit_system.dto.CreditGapRequest;
import com.softlend.credit_system.dto.CreditGapResponse;
import com.softlend.credit_system.service.CreditGapService;

@RestController
@RequestMapping("/credit-gaps")
public class CreditGapController {
	@Autowired
	private CreditGapService service;
	
	@PostMapping("/{id}/credit-gaps")
	public ResponseEntity<CreditGapResponse>
	createGap(
	        @PathVariable Long id,
	        @RequestBody CreditGapRequest request) {

	    return new ResponseEntity<>(
	            service.createGap(id, request),
	            HttpStatus.CREATED);
}
	
	@PatchMapping("/{id}/resolve")
	public ResponseEntity<CreditGapResponse>
	resolveGap(@PathVariable Long id) {

	    return ResponseEntity.ok(
	            service.resolveGap(id));
	}
}

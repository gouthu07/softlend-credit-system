package com.softlend.credit_system.service;

import java.util.List;

import com.softlend.credit_system.dto.OfferListResponse;
import com.softlend.credit_system.dto.OfferRequest;
import com.softlend.credit_system.dto.OfferResponse;
import com.softlend.credit_system.dto.OfferStatusRequest;

public interface OfferService {
	OfferResponse createOffer(
	        Long customerId,
	        OfferRequest request);
	
	List<OfferListResponse> getOffers(Long customerId);
	OfferResponse updateStatus(
	        Long offerId,
	        OfferStatusRequest request);
}

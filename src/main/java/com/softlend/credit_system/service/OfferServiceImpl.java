package com.softlend.credit_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softlend.credit_system.dto.OfferListResponse;
import com.softlend.credit_system.dto.OfferRequest;
import com.softlend.credit_system.dto.OfferResponse;
import com.softlend.credit_system.dto.OfferStatusRequest;
import com.softlend.credit_system.entity.Customer;
import com.softlend.credit_system.entity.Offer;
import com.softlend.credit_system.enums.OfferStatus;
import com.softlend.credit_system.exception.CustomerNotFoundException;
import com.softlend.credit_system.exception.OfferLockedException;
import com.softlend.credit_system.exception.OfferNotFoundException;
import com.softlend.credit_system.repository.CustomerRepository;
import com.softlend.credit_system.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CustomerRepository customerRepository;
	@Override
	public OfferResponse createOffer(Long customerId, OfferRequest request) {
		Customer customer = customerRepository.findById(customerId)
		        .orElseThrow(() ->
		                new CustomerNotFoundException(
		                        "Customer not found"));

		Offer offer = new Offer();

		offer.setLender(request.getLender());
		offer.setAmount(request.getAmount());
		offer.setInterestRate(request.getInterestRate());
		offer.setTenureMonths(request.getTenureMonths());
		offer.setMinScoreRequired(request.getMinScoreRequired());

		offer.setStatus(OfferStatus.PENDING);

		offer.setCustomer(customer);

		Offer saved = offerRepository.save(offer);
		
		return OfferResponse.builder()
		        .id(saved.getId())
		        .lender(saved.getLender())
		        .amount(saved.getAmount())
		        .interestRate(saved.getInterestRate())
		        .tenureMonths(saved.getTenureMonths())
		        .minScoreRequired(saved.getMinScoreRequired())
		        .status(saved.getStatus().name())
		        .build();
	}
	@Override
	public List<OfferListResponse> getOffers(Long customerId) {
		 Customer customer = customerRepository.findById(customerId)
		            .orElseThrow(() ->
		                    new CustomerNotFoundException(
		                            "Customer not found"));

		    List<Offer> offers =
		            offerRepository.findByCustomerId(customerId);

		    return offers.stream()
		            .map(offer -> {

		                boolean locked =
		                        customer.getCibil_score()
		                        < offer.getMinScoreRequired();

		                int scoreGap = locked
		                        ? offer.getMinScoreRequired()
		                        - customer.getCibil_score()
		                        : 0;

		                return OfferListResponse.builder()
		                        .id(offer.getId())
		                        .lender(offer.getLender())
		                        .amount(offer.getAmount())
		                        .interestRate(
		                                offer.getInterestRate())
		                        .tenureMonths(
		                                offer.getTenureMonths())
		                        .minScoreRequired(
		                                offer.getMinScoreRequired())
		                        .status(
		                                offer.getStatus().name())
		                        .locked(locked)
		                        .scoreGap(scoreGap)
		                        .build();
		            })
		            .toList();
	}
	@Override
	public OfferResponse updateStatus(Long offerId, OfferStatusRequest request) {
		Offer offer = offerRepository.findById(offerId)
		        .orElseThrow(() ->
		                new OfferNotFoundException(
		                        "Offer not found"));
	    OfferStatus newStatus =
	            OfferStatus.valueOf(
	                    request.getStatus().toUpperCase());

	    Customer customer = offer.getCustomer();

	    boolean locked =
	            customer.getCibil_score()
	            < offer.getMinScoreRequired();

	    // PENDING -> ACTIVE
	    if (offer.getStatus() == OfferStatus.PENDING
	            && newStatus == OfferStatus.ACTIVE) {

	        if (locked) {
	        	throw new OfferLockedException(
	        	        "Offer is locked. Customer score "
	        	        + customer.getCibil_score()
	        	        + " is below required "
	        	        + offer.getMinScoreRequired());
	        }

	        offer.setStatus(OfferStatus.ACTIVE);
	    }

	    // ACTIVE -> DISBURSED
	    else if (offer.getStatus() == OfferStatus.ACTIVE
	            && newStatus == OfferStatus.DISBURSED) {

	        offer.setStatus(OfferStatus.DISBURSED);
	    }

	    // Invalid transition
	    else {
	        throw new RuntimeException(
	                "Invalid offer status transition");
	    }

	    Offer saved = offerRepository.save(offer);

	    return OfferResponse.builder()
	            .id(saved.getId())
	            .lender(saved.getLender())
	            .amount(saved.getAmount())
	            .interestRate(saved.getInterestRate())
	            .tenureMonths(saved.getTenureMonths())
	            .minScoreRequired(saved.getMinScoreRequired())
	            .status(saved.getStatus().name())
	            .build();
	}

}

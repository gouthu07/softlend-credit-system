package com.softlend.credit_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softlend.credit_system.dto.EmiResponse;
import com.softlend.credit_system.entity.Offer;
import com.softlend.credit_system.exception.OfferNotFoundException;
import com.softlend.credit_system.repository.OfferRepository;

@Service
public class EmiResponseServiceImpl implements EmiResponseService {
	@Autowired
    private OfferRepository offerRepository;
	@Override
	public EmiResponse calculateEmi(Long offerId) {
		Offer offer = offerRepository.findById(offerId)
		        .orElseThrow(() ->
		                new OfferNotFoundException(
		                        "Offer not found"));

	    double p = offer.getAmount().doubleValue();

	    double r =
	            offer.getInterestRate()
	                    .doubleValue()
	                    / 12
	                    / 100;

	    int n = offer.getTenureMonths();

	    double emi =
	            (p * r * Math.pow(1 + r, n))
	                    /
	            (Math.pow(1 + r, n) - 1);

	    return EmiResponse.builder()
	            .offerId(offer.getId())
	            .principal(offer.getAmount())
	            .interestRate(
	                    offer.getInterestRate())
	            .tenureMonths(
	                    offer.getTenureMonths())
	            .monthlyEmi(
	                    Math.round(emi * 100.0)
	                    / 100.0)
	            .build();
	}

}

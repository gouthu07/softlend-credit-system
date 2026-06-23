package com.softlend.credit_system.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OfferRequest {
	   private String lender;

	    private BigDecimal amount;

	    private BigDecimal interestRate;

	    private Integer tenureMonths;

	    private Integer minScoreRequired;
}

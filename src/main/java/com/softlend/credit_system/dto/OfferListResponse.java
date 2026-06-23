package com.softlend.credit_system.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OfferListResponse {
	 private Long id;

	    private String lender;

	    private BigDecimal amount;

	    private BigDecimal interestRate;

	    private Integer tenureMonths;

	    private Integer minScoreRequired;

	    private String status;

	    private Boolean locked;

	    private Integer scoreGap;
}

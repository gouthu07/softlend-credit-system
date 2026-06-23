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
public class EmiResponse {
    private Long offerId;

    private BigDecimal principal;

    private BigDecimal interestRate;

    private Integer tenureMonths;

    private Double monthlyEmi;

}

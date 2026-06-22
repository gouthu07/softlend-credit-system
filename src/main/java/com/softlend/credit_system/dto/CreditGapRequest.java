package com.softlend.credit_system.dto;

import lombok.Data;

@Data
public class CreditGapRequest {
	private String factor;

    private String currentValue;

    private String idealValue;

    private String impact;

    private Integer estimatedScoreGain;

    private String actionDescription;
}

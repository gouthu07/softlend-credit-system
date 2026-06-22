package com.softlend.credit_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditGapResponse {

	 private Long id;

	    private String factor;

	    private String impact;

	    private Integer estimatedScoreGain;

	    private String actionDescription;

	    private String status;
}

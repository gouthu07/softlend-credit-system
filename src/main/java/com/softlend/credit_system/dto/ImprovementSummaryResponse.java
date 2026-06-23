package com.softlend.credit_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImprovementSummaryResponse {
	  private Long customerId;

	    private Long resolvedGaps;

	    private Integer totalRecoveredPoints;

	    private Integer remainingPotentialGain;
}

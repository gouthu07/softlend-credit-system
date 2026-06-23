package com.softlend.credit_system.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditProfileResponse {

    private Long customerId;

    private String name;

    private Integer cibilScore;

    private LocalDateTime scoreFetchedAt;

    private Integer potentialScore;

    private List<CreditGapResponse> gaps;

    private Long openGaps;

    private Long resolvedGaps;
}

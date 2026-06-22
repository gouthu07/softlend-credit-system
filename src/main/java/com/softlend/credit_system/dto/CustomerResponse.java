package com.softlend.credit_system.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
	 private Long id;

	    private String name;

	    private Integer cibilScore;

	    private LocalDateTime scoreFetchedAt;
}

package com.softlend.credit_system.service;

import com.softlend.credit_system.dto.CreditGapRequest;
import com.softlend.credit_system.dto.CreditGapResponse;

public interface CreditGapService {

	CreditGapResponse createGap(Long customerId,
            CreditGapRequest request);
	
	CreditGapResponse resolveGap(Long gapId);
}

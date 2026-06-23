package com.softlend.credit_system.service;

import com.softlend.credit_system.dto.EmiResponse;

public interface EmiResponseService {
	EmiResponse calculateEmi(Long offerId);
}

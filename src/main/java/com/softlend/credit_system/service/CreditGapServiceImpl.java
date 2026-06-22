package com.softlend.credit_system.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softlend.credit_system.dto.CreditGapRequest;
import com.softlend.credit_system.dto.CreditGapResponse;
import com.softlend.credit_system.entity.CreditGap;
import com.softlend.credit_system.entity.Customer;
import com.softlend.credit_system.enums.Gapstatus;
import com.softlend.credit_system.exception.CustomerNotFoundException;
import com.softlend.credit_system.repository.CreditGapRepository;
import com.softlend.credit_system.repository.CustomerRepository;

@Service
public class CreditGapServiceImpl implements CreditGapService{

	 @Autowired
	    private CreditGapRepository creditGapRepository;

	    @Autowired
	    private CustomerRepository customerRepository;
	@Override
	public CreditGapResponse createGap(Long customerId, CreditGapRequest request) {
		

    Customer customer =
            customerRepository.findById(customerId)
            .orElseThrow(() ->
                    new CustomerNotFoundException(
                            "Customer not found"));

    CreditGap gap = new CreditGap();

    gap.setFactor(request.getFactor());
    gap.setCurrentValue(request.getCurrentValue());
    gap.setIdealValue(request.getIdealValue());
    gap.setImpact(request.getImpact());
    gap.setEstimatedScoreGain(
            request.getEstimatedScoreGain());
    gap.setActionDescription(
            request.getActionDescription());

    gap.setStatus(Gapstatus.OPEN);

    gap.setResolvedAt(null);

    gap.setCustomer(customer);
    
    System.out.println(request.getFactor());
    System.out.println(request.getCurrentValue());

    CreditGap saved =
            creditGapRepository.save(gap);

    return CreditGapResponse.builder()
            .id(saved.getId())
            .factor(saved.getFactor())
            .impact(saved.getImpact())
            .estimatedScoreGain(
                    saved.getEstimatedScoreGain())
            .actionDescription(
                    saved.getActionDescription())
            .status(saved.getStatus().name())
            .build();
	}
	@Override
	public CreditGapResponse resolveGap(Long gapId) {
		 CreditGap gap = creditGapRepository.findById(gapId)
		            .orElseThrow(() ->
		                    new RuntimeException("Gap not found"));

		    gap.setStatus(Gapstatus.RESOLVED);

		    gap.setResolvedAt(LocalDateTime.now());

		    CreditGap saved = creditGapRepository.save(gap);

		    return CreditGapResponse.builder()
		            .id(saved.getId())
		            .factor(saved.getFactor())
		            .impact(saved.getImpact())
		            .estimatedScoreGain(saved.getEstimatedScoreGain())
		            .actionDescription(saved.getActionDescription())
		            .status(saved.getStatus().name())
		            .build();
	}
	
}

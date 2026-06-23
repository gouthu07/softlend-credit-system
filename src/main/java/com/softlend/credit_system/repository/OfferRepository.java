package com.softlend.credit_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softlend.credit_system.entity.Offer;
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
	List<Offer> findByCustomerId(Long customerId);
	
}

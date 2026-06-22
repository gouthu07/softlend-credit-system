package com.softlend.credit_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softlend.credit_system.entity.CreditGap;

@Repository
public interface CreditGapRepository extends JpaRepository<CreditGap, Long> {

}

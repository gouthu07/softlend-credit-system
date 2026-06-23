package com.softlend.credit_system.entity;

import java.math.BigDecimal;

import com.softlend.credit_system.enums.OfferStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String lender;

	    @Column(name = "amount")
	    private BigDecimal amount;

	    @Column(name = "interest_rate")
	    private BigDecimal interestRate;

	    @Column(name = "tenure_months")
	    private Integer tenureMonths;

	    @Column(name = "min_score_required")
	    private Integer minScoreRequired;

	    @Enumerated(EnumType.STRING)
	    private OfferStatus status;

	    @ManyToOne
	    @JoinColumn(name="customer_id")
	    private Customer customer;
}

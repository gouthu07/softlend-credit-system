package com.softlend.credit_system.entity;

import java.time.LocalDateTime;

import com.softlend.credit_system.enums.Gapstatus;

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
@Table(name="credit_gaps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditGap {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String factor;

    private String currentValue;

    private String idealValue;

    private String impact;

    private Integer estimatedScoreGain;

    private String actionDescription;

    @Enumerated(EnumType.STRING)
    private Gapstatus status;

    private LocalDateTime resolvedAt;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}

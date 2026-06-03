package com.cribup.management.dto;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;

public class RevenueSummaryDTO {
	private BigDecimal totalCollected;
	private BigDecimal totalPending;
	private Map<YearMonth, BigDecimal> monthlyRevenue;
	private Long totalPaidOwners;
	private Long totalPendingOwners;

	// Getters and Setters
	public BigDecimal getTotalCollected() {
		return totalCollected;
	}

	public void setTotalCollected(BigDecimal totalCollected) {
		this.totalCollected = totalCollected;
	}

	public BigDecimal getTotalPending() {
		return totalPending;
	}

	public void setTotalPending(BigDecimal totalPending) {
		this.totalPending = totalPending;
	}

	public Map<YearMonth, BigDecimal> getMonthlyRevenue() {
		return monthlyRevenue;
	}

	public void setMonthlyRevenue(Map<YearMonth, BigDecimal> monthlyRevenue) {
		this.monthlyRevenue = monthlyRevenue;
	}

	public Long getTotalPaidOwners() {
		return totalPaidOwners;
	}

	public void setTotalPaidOwners(Long totalPaidOwners) {
		this.totalPaidOwners = totalPaidOwners;
	}

	public Long getTotalPendingOwners() {
		return totalPendingOwners;
	}

	public void setTotalPendingOwners(Long totalPendingOwners) {
		this.totalPendingOwners = totalPendingOwners;
	}
}
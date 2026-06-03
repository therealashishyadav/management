package com.cribup.management.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DashboardStatsDTO {
	private Long totalPGs;
	private Long totalUsers;
	private Long totalOwners;
	private Long totalTenants;
	private Long monthlyInquiries;
	private Long weeklySignups;
	private BigDecimal totalFinderFeesCollected;
	private BigDecimal pendingFinderFees;
	private Boolean platformHealthy;
	private LocalDateTime lastUpdated;

	// Getters and setters
	public Long getTotalPGs() {
		return totalPGs;
	}

	public void setTotalPGs(Long totalPGs) {
		this.totalPGs = totalPGs;
	}

	public Long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public Long getTotalOwners() {
		return totalOwners;
	}

	public void setTotalOwners(Long totalOwners) {
		this.totalOwners = totalOwners;
	}

	public Long getTotalTenants() {
		return totalTenants;
	}

	public void setTotalTenants(Long totalTenants) {
		this.totalTenants = totalTenants;
	}

	public Long getMonthlyInquiries() {
		return monthlyInquiries;
	}

	public void setMonthlyInquiries(Long monthlyInquiries) {
		this.monthlyInquiries = monthlyInquiries;
	}

	public Long getWeeklySignups() {
		return weeklySignups;
	}

	public void setWeeklySignups(Long weeklySignups) {
		this.weeklySignups = weeklySignups;
	}

	public BigDecimal getTotalFinderFeesCollected() {
		return totalFinderFeesCollected;
	}

	public void setTotalFinderFeesCollected(BigDecimal totalFinderFeesCollected) {
		this.totalFinderFeesCollected = totalFinderFeesCollected;
	}

	public BigDecimal getPendingFinderFees() {
		return pendingFinderFees;
	}

	public void setPendingFinderFees(BigDecimal pendingFinderFees) {
		this.pendingFinderFees = pendingFinderFees;
	}

	public Boolean getPlatformHealthy() {
		return platformHealthy;
	}

	public void setPlatformHealthy(Boolean platformHealthy) {
		this.platformHealthy = platformHealthy;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
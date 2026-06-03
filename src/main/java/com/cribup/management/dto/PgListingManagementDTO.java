package com.cribup.management.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PgListingManagementDTO {
	private Long id;
	private String pgName;
	private String city;
	private String locality;
	private String ownerName;
	private Long ownerId;
	private Boolean verified;
	private Boolean active;
	private BigDecimal lowestPrice;
	private LocalDateTime createdAt;
	private Long inquiryCount;
	private String occupancyType;

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPgName() {
		return pgName;
	}

	public void setPgName(String pgName) {
		this.pgName = pgName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getInquiryCount() {
		return inquiryCount;
	}

	public void setInquiryCount(Long inquiryCount) {
		this.inquiryCount = inquiryCount;
	}

	public String getOccupancyType() {
		return occupancyType;
	}

	public void setOccupancyType(String occupancyType) {
		this.occupancyType = occupancyType;
	}
}
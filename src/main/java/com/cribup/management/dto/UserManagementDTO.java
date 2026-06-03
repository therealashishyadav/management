package com.cribup.management.dto;

import java.time.LocalDateTime;

public class UserManagementDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String role;
	private Boolean active;
	private LocalDateTime createdAt;
	private Long listedPGCount;
	private Long inquiryCount;

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getListedPGCount() {
		return listedPGCount;
	}

	public void setListedPGCount(Long listedPGCount) {
		this.listedPGCount = listedPGCount;
	}

	public Long getInquiryCount() {
		return inquiryCount;
	}

	public void setInquiryCount(Long inquiryCount) {
		this.inquiryCount = inquiryCount;
	}
}
package com.cribup.management.dto;

public class ChartDataDTO {
	private String label;
	private Long value;

	public ChartDataDTO() {
	}

	public ChartDataDTO(String label, Long value) {
		this.label = label;
		this.value = value;
	}

	// Getters and setters
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}
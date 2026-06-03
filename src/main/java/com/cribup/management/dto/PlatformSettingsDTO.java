package com.cribup.management.dto;

import java.util.List;

public class PlatformSettingsDTO {
	private List<Long> featuredListings;
	private List<String> cities;
	private List<String> localities;
	private String announcementTitle;
	private String announcementMessage;

	// Getters and setters
	public List<Long> getFeaturedListings() {
		return featuredListings;
	}

	public void setFeaturedListings(List<Long> featuredListings) {
		this.featuredListings = featuredListings;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public List<String> getLocalities() {
		return localities;
	}

	public void setLocalities(List<String> localities) {
		this.localities = localities;
	}

	public String getAnnouncementTitle() {
		return announcementTitle;
	}

	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}

	public String getAnnouncementMessage() {
		return announcementMessage;
	}

	public void setAnnouncementMessage(String announcementMessage) {
		this.announcementMessage = announcementMessage;
	}
}
package com.cribup.management.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cribup.management.config.FeignClientConfig;
import com.cribup.management.dto.PageResponse;
import com.cribup.management.dto.PgListingManagementDTO;

@FeignClient(name = "add-pg-service", url = "${services.add-pg-service.url}", configuration = FeignClientConfig.class)

public interface PgServiceClient {

	@GetMapping("/api/pg-listings")
	PageResponse<PgListingManagementDTO> getAllListings(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "1000") int size);

	@PutMapping("/api/pg-listings/{id}/verify")
	void verifyListing(@PathVariable("id") Long id);

	@DeleteMapping("/api/pg-listings/{id}")
    void deleteListing(@PathVariable("id") Long id);
}
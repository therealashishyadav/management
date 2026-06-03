package com.cribup.management.client;

import com.cribup.management.dto.PgListingManagementDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ADD-PG-SERVICE", url = "${services.add-pg-service.url}")
public interface PgServiceClient {

	@GetMapping("/api/pg-listings/all")
	List<PgListingManagementDTO> getAllListings();

	@PutMapping("/api/pg-listings/{id}/verify")
	void verifyListing(@PathVariable("id") Long id);

	@DeleteMapping("/api/pg-listings/{id}")
	void deleteListing(@PathVariable("id") Long id);
}
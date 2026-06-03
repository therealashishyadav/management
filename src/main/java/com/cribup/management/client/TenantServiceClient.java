package com.cribup.management.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "TENANT-SERVICE", url = "${services.tenant-service.url}")
public interface TenantServiceClient {

	@GetMapping("/api/tenants/owner/{ownerId}/count")
	Long getTenantCountByOwner(@PathVariable("ownerId") Long ownerId);
}
package com.cribup.management.client;

import com.cribup.management.dto.UserManagementDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ACCOUNT-SERVICE", url = "${services.account-service.url}")
public interface AccountServiceClient {

	@GetMapping("/api/v1/user/all")
	List<UserManagementDTO> getAllUsers();

	@PutMapping("/api/v1/user/{id}/activate")
	void activateUser(@PathVariable("id") Long id);

	@PutMapping("/api/v1/user/{id}/deactivate")
	void deactivateUser(@PathVariable("id") Long id);
}
package com.cribup.management.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.cribup.management.dto.UserManagementDTO;

@FeignClient(name = "ACCOUNT-SERVICE", url = "${services.account-service.url}")
public interface AccountServiceClient {

	@GetMapping("/api/v1/user/")
	List<UserManagementDTO> getAllUsers();

	@PutMapping("/api/v1/user/{id}/activate")
	void activateUser(@PathVariable("id") Long id);

	@PutMapping("/api/v1/user/{id}/deactivate")
	void deactivateUser(@PathVariable("id") Long id);
}
package com.cribup.management.client;

import com.cribup.management.dto.InquiryManagementDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "INQUIRY-SERVICE", url = "${services.inquiry-service.url}")
public interface InquiryServiceClient {

	@GetMapping("/inquiry/get_all_message")
	List<InquiryManagementDTO> getAllInquiries();

	@GetMapping("/inquiry/search")
	List<InquiryManagementDTO> searchInquiries(@RequestParam(required = false) String fullName,
			@RequestParam(required = false) String email, @RequestParam(required = false) String phone,
			@RequestParam(required = false) String location, @RequestParam(required = false) String inquiryType);
}
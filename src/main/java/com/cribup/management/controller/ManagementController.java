package com.cribup.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cribup.management.dto.ChartDataDTO;
import com.cribup.management.dto.DashboardStatsDTO;
import com.cribup.management.dto.InquiryManagementDTO;
import com.cribup.management.dto.PgListingManagementDTO;
import com.cribup.management.dto.PlatformSettingsDTO;
import com.cribup.management.dto.RevenueSummaryDTO;
import com.cribup.management.dto.UserManagementDTO;
import com.cribup.management.service.ManagementService;

@RestController
@RequestMapping("/api/management")
public class ManagementController {

    @Autowired
    private ManagementService managementService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(managementService.getDashboardStats());
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> getHealth() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserManagementDTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(managementService.getAllUsers(pageable, search));
    }

    @PutMapping("/users/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable Long id) {
        managementService.activateUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {
        managementService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pgs")
    public ResponseEntity<Page<PgListingManagementDTO>> getAllListings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String occupancyType) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(managementService.getAllListings(pageable, city, occupancyType));
    }

    @PutMapping("/pgs/{id}/verify")
    public ResponseEntity<Void> verifyListing(@PathVariable Long id) {
        managementService.verifyListing(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/pgs/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable Long id) {
        managementService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/inquiries")
    public ResponseEntity<Page<InquiryManagementDTO>> getAllInquiries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String inquiryType) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(managementService.getAllInquiries(pageable, search, location, inquiryType));
    }

    @GetMapping("/inquiries/export")
    public ResponseEntity<String> exportInquiriesToCsv() {
        String csv = managementService.exportInquiriesToCsv();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=inquiries.csv")
                .body(csv);
    }

    @GetMapping("/revenue")
    public ResponseEntity<RevenueSummaryDTO> getRevenueSummary() {
        return ResponseEntity.ok(managementService.getRevenueSummary());
    }

    @GetMapping("/revenue/owner/{ownerId}/history")
    public ResponseEntity<List<Object>> getOwnerPaymentHistory(@PathVariable Long ownerId) {
        return ResponseEntity.ok(managementService.getOwnerPaymentHistory(ownerId));
    }

    @GetMapping("/reports/city-distribution")
    public ResponseEntity<List<ChartDataDTO>> getCityDistribution() {
        return ResponseEntity.ok(managementService.getCityDistribution());
    }

    @GetMapping("/reports/monthly-growth")
    public ResponseEntity<List<ChartDataDTO>> getMonthlyGrowth() {
        return ResponseEntity.ok(managementService.getMonthlyGrowth());
    }

    @GetMapping("/reports/inquiry-conversion")
    public ResponseEntity<Double> getInquiryConversionRate() {
        return ResponseEntity.ok(managementService.getInquiryConversionRate());
    }

    @GetMapping("/settings")
    public ResponseEntity<PlatformSettingsDTO> getPlatformSettings() {
        return ResponseEntity.ok(managementService.getPlatformSettings());
    }

    @PutMapping("/settings")
    public ResponseEntity<Void> updatePlatformSettings(@RequestBody PlatformSettingsDTO settings) {
        managementService.updatePlatformSettings(settings);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/settings/announce")
    public ResponseEntity<Void> broadcastAnnouncement(@RequestBody PlatformSettingsDTO announcement) {
        managementService.broadcastAnnouncement(announcement);
        return ResponseEntity.ok().build();
    }
}
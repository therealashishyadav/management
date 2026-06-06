package com.cribup.management.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cribup.management.client.AccountServiceClient;
import com.cribup.management.client.InquiryServiceClient;
import com.cribup.management.client.PgServiceClient;
import com.cribup.management.client.TenantServiceClient;
import com.cribup.management.dto.ChartDataDTO;
import com.cribup.management.dto.DashboardStatsDTO;
import com.cribup.management.dto.InquiryManagementDTO;
import com.cribup.management.dto.PgListingManagementDTO;
import com.cribup.management.dto.PlatformSettingsDTO;
import com.cribup.management.dto.RevenueSummaryDTO;
import com.cribup.management.dto.UserManagementDTO;

@Service
public class ManagementService {

    @Autowired
    private AccountServiceClient accountClient;

    @Autowired
    private PgServiceClient pgClient;

    @Autowired
    private InquiryServiceClient inquiryClient;

    @Autowired
    private TenantServiceClient tenantClient;

    public DashboardStatsDTO getDashboardStats() {
        DashboardStatsDTO stats = new DashboardStatsDTO();
        List<UserManagementDTO> users = accountClient.getAllUsers();
        List<PgListingManagementDTO> pgs = pgClient.getAllListings(0, 1000).getContent();
        List<InquiryManagementDTO> inquiries = inquiryClient.getAllInquiries();

        stats.setTotalPGs((long) pgs.size());
        stats.setTotalUsers((long) users.size());
        stats.setTotalOwners(users.stream().filter(u -> "OWNER".equals(u.getRole())).count());
        stats.setTotalTenants(users.stream().filter(u -> "USER".equals(u.getRole())).count());

        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        stats.setMonthlyInquiries(inquiries.stream()
                .filter(i -> i.getCreatedAt() != null && i.getCreatedAt().isAfter(oneMonthAgo))
                .count());

        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        stats.setWeeklySignups(users.stream()
                .filter(u -> u.getCreatedAt() != null && u.getCreatedAt().isAfter(oneWeekAgo))
                .count());

        stats.setTotalFinderFeesCollected(BigDecimal.ZERO);
        stats.setPendingFinderFees(BigDecimal.ZERO);
        stats.setPlatformHealthy(true);
        stats.setLastUpdated(LocalDateTime.now());

        return stats;
    }

    public Page<UserManagementDTO> getAllUsers(Pageable pageable, String search) {
        List<UserManagementDTO> users = accountClient.getAllUsers();
        if (search != null && !search.isEmpty()) {
            String lowerSearch = search.toLowerCase();
            users = users.stream()
                    .filter(u -> u.getFirstName().toLowerCase().contains(lowerSearch)
                            || u.getLastName().toLowerCase().contains(lowerSearch)
                            || u.getEmail().toLowerCase().contains(lowerSearch)
                            || u.getPhone().contains(search))
                    .collect(Collectors.toList());
        }
        List<PgListingManagementDTO> allListings = pgClient.getAllListings(0, 1000).getContent();
        List<InquiryManagementDTO> allInquiries = inquiryClient.getAllInquiries();
        for (UserManagementDTO user : users) {
            if ("OWNER".equals(user.getRole())) {
                user.setListedPGCount((long) allListings.stream()
                        .filter(p -> p.getOwnerId().equals(user.getId())).count());
            }
            user.setInquiryCount((long) allInquiries.stream()
                    .filter(i -> i.getEmail().equals(user.getEmail())).count());
        }
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), users.size());
        return new PageImpl<>(users.subList(start, end), pageable, users.size());
    }

    public void activateUser(Long id) {
        accountClient.activateUser(id);
    }

    public void deactivateUser(Long id) {
        accountClient.deactivateUser(id);
    }

    public Page<PgListingManagementDTO> getAllListings(Pageable pageable, String city, String occupancyType) {
        List<PgListingManagementDTO> pgs = pgClient.getAllListings(0, 1000).getContent();
        if (city != null && !city.isEmpty()) {
            pgs = pgs.stream().filter(p -> city.equalsIgnoreCase(p.getCity())).collect(Collectors.toList());
        }
        if (occupancyType != null && !occupancyType.isEmpty()) {
            pgs = pgs.stream().filter(p -> occupancyType.equalsIgnoreCase(p.getOccupancyType()))
                    .collect(Collectors.toList());
        }
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), pgs.size());
        return new PageImpl<>(pgs.subList(start, end), pageable, pgs.size());
    }

    public void verifyListing(Long id) {
        pgClient.verifyListing(id);
    }

    public void deleteListing(Long id) {
        pgClient.deleteListing(id);
    }

    public Page<InquiryManagementDTO> getAllInquiries(Pageable pageable, String search, String location, String inquiryType) {
        List<InquiryManagementDTO> inquiries = inquiryClient.getAllInquiries();
        if (search != null && !search.isEmpty()) {
            String lowerSearch = search.toLowerCase();
            inquiries = inquiries.stream()
                    .filter(i -> i.getFullName().toLowerCase().contains(lowerSearch)
                            || i.getEmail().toLowerCase().contains(lowerSearch)
                            || i.getPhone().contains(search))
                    .collect(Collectors.toList());
        }
        if (location != null && !location.isEmpty()) {
            inquiries = inquiries.stream().filter(i -> location.equalsIgnoreCase(i.getLocation()))
                    .collect(Collectors.toList());
        }
        if (inquiryType != null && !inquiryType.isEmpty()) {
            inquiries = inquiries.stream().filter(i -> inquiryType.equalsIgnoreCase(i.getInquiryType()))
                    .collect(Collectors.toList());
        }
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), inquiries.size());
        return new PageImpl<>(inquiries.subList(start, end), pageable, inquiries.size());
    }

    public String exportInquiriesToCsv() {
        List<InquiryManagementDTO> inquiries = inquiryClient.getAllInquiries();
        StringBuilder csv = new StringBuilder();
        csv.append("ID,Full Name,Email,Phone,Location,Inquiry Type,Message,Created At\n");
        for (InquiryManagementDTO i : inquiries) {
            csv.append(i.getId()).append(",")
                    .append(escapeCsv(i.getFullName())).append(",")
                    .append(escapeCsv(i.getEmail())).append(",")
                    .append(i.getPhone()).append(",")
                    .append(escapeCsv(i.getLocation())).append(",")
                    .append(escapeCsv(i.getInquiryType())).append(",")
                    .append(escapeCsv(i.getMessage())).append(",")
                    .append(i.getCreatedAt()).append("\n");
        }
        return csv.toString();
    }

    private String escapeCsv(String str) {
        if (str == null) return "";
        if (str.contains(",") || str.contains("\"")) {
            str = str.replace("\"", "\"\"");
            return "\"" + str + "\"";
        }
        return str;
    }

    public RevenueSummaryDTO getRevenueSummary() {
        RevenueSummaryDTO summary = new RevenueSummaryDTO();
        summary.setTotalCollected(BigDecimal.ZERO);
        summary.setTotalPending(BigDecimal.ZERO);
        summary.setMonthlyRevenue(Map.of());
        summary.setTotalPaidOwners(0L);
        summary.setTotalPendingOwners(0L);
        return summary;
    }

    public List<Object> getOwnerPaymentHistory(Long ownerId) {
        return List.of();
    }

    public List<ChartDataDTO> getCityDistribution() {
        List<PgListingManagementDTO> pgs = pgClient.getAllListings(0, 1000).getContent();
        Map<String, Long> cityCount = pgs.stream()
                .collect(Collectors.groupingBy(PgListingManagementDTO::getCity, Collectors.counting()));
        return cityCount.entrySet().stream()
                .map(e -> new ChartDataDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public List<ChartDataDTO> getMonthlyGrowth() {
        List<UserManagementDTO> users = accountClient.getAllUsers();
        Map<YearMonth, Long> signups = users.stream()
                .filter(u -> u.getCreatedAt() != null)
                .collect(Collectors.groupingBy(u -> YearMonth.from(u.getCreatedAt()), Collectors.counting()));
        return signups.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new ChartDataDTO(e.getKey().toString(), e.getValue()))
                .collect(Collectors.toList());
    }

    public Double getInquiryConversionRate() {
        List<InquiryManagementDTO> inquiries = inquiryClient.getAllInquiries();
        return (double) inquiries.size() * 0.3;
    }

    public PlatformSettingsDTO getPlatformSettings() {
        PlatformSettingsDTO settings = new PlatformSettingsDTO();
        settings.setFeaturedListings(List.of(1L, 2L, 3L));
        settings.setCities(List.of("Pune", "Mumbai", "Bangalore"));
        settings.setLocalities(List.of("Hinjewadi", "Koregaon Park", "Viman Nagar"));
        settings.setAnnouncementTitle("");
        settings.setAnnouncementMessage("");
        return settings;
    }

    public void updatePlatformSettings(PlatformSettingsDTO settings) {
        // Save to database or config file
    }

    public void broadcastAnnouncement(PlatformSettingsDTO announcement) {
        // Send email/notification to all owners/users
    }
}
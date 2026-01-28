/*
 * package com.dhatvibs.controller;
 * 
 * import com.dhatvibs.service.HomeIncentiveService; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import java.util.List; import java.util.Map;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/home") public class IncentiveController {
 * 
 * private final HomeIncentiveService service;
 * 
 * public IncentiveController(HomeIncentiveService service) { this.service =
 * service; }
 * 
 * @GetMapping("/peakhours-incentives") public Map<String, Object> peakHours() {
 * return service.getPeakHourIncentives(); }
 * 
 * @GetMapping("/incentives/weekly-earning") public Map<String, Object> weekly(
 * 
 * @RequestHeader("X-RIDER-ID") Long riderId) { return
 * service.getWeeklyEarning(riderId); }
 * 
 * @GetMapping("/incentives/daily-earning") public Map<String, Object> daily(
 * 
 * @RequestHeader("X-RIDER-ID") Long riderId) { return
 * service.getDailyEarning(riderId); } }
 */ 


package com.dhatvibs.controller;

import com.dhatvibs.service.HomeIncentiveService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/home")
public class IncentiveController {

    private final HomeIncentiveService service;

    public IncentiveController(HomeIncentiveService service) {
        this.service = service;
    }

    // ⏰ Peak hours incentives (no rider-specific data)
    @GetMapping("/peakhours-incentives")
    public Map<String, Object> peakHours() {
        return service.getPeakHourIncentives();
    }

    // 💰 Weekly earning incentives
    @GetMapping("/incentives/weekly-earning")
    public Map<String, Object> weekly() {

        Long riderId = getRiderIdFromToken();
        return service.getWeeklyEarning(riderId);
    }

    // 📅 Daily earning incentives
    @GetMapping("/incentives/daily-earning")
    public Map<String, Object> daily() {

        Long riderId = getRiderIdFromToken();
        return service.getDailyEarning(riderId);
    }

    // 🔐 Helper method
    private Long getRiderIdFromToken() {
        return (Long) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}

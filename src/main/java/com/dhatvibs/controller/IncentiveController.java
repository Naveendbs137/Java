package com.dhatvibs.controller;

import com.dhatvibs.service.HomeIncentiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/home")
public class IncentiveController {

    private final HomeIncentiveService service;

    public IncentiveController(HomeIncentiveService service) {
        this.service = service;
    }

    @GetMapping("/peakhours-incentives")
    public Map<String, Object> peakHours() {
        return service.getPeakHourIncentives();
    }

    @GetMapping("/incentives/weekly-earning")
    public Map<String, Object> weekly(
            @RequestHeader("X-RIDER-ID") Long riderId) {
        return service.getWeeklyEarning(riderId);
    }

    @GetMapping("/incentives/daily-earning")
    public Map<String, Object> daily(
            @RequestHeader("X-RIDER-ID") Long riderId) {
        return service.getDailyEarning(riderId);
    }
}

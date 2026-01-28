/*
 * package com.dhatvibs.controller;
 * 
 * import com.dhatvibs.dto.*; import com.dhatvibs.service.EarningsService;
 * import org.springframework.format.annotation.DateTimeFormat; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import java.time.LocalDate; import java.time.YearMonth;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/earnings") public class EarningsController {
 * 
 * private final EarningsService earningsService;
 * 
 * public EarningsController(EarningsService earningsService) {
 * this.earningsService = earningsService; }
 * 
 * @GetMapping("/week") public WeekWiseEarningsResponseDTO getWeekly(
 * 
 * @RequestParam Long riderId,
 * 
 * @RequestParam
 * 
 * @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
 * 
 * @RequestParam
 * 
 * @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
 * 
 * return earningsService.getWeeklyEarnings(riderId, from, to); }
 * 
 * @GetMapping("/orders/{orderId}") public OrderEarningsDTO getOrderEarnings(
 * 
 * @RequestParam Long riderId,
 * 
 * @PathVariable String orderId) {
 * 
 * return earningsService.getOrderEarnings(riderId, orderId); }
 * 
 * @GetMapping("/{date}") public DayWiseEarningsDTO getDayWise(
 * 
 * @RequestParam Long riderId,
 * 
 * @PathVariable
 * 
 * @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
 * 
 * return earningsService.getDayWiseEarnings(riderId, date); }
 * 
 * @GetMapping("/month") public WeekWiseEarningsResponseDTO getMonthly(
 * 
 * @RequestParam Long riderId,
 * 
 * @RequestParam String month) {
 * 
 * return earningsService.getMonthlyEarnings( riderId, YearMonth.parse(month));
 * }
 * 
 * @GetMapping("/summary") public EarningsSummaryDTO getSummary(
 * 
 * @RequestParam Long riderId) {
 * 
 * return earningsService.getEarningsSummary(riderId); } }
 */ 

package com.dhatvibs.controller;

import com.dhatvibs.dto.*;
import com.dhatvibs.service.EarningsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;

@RestController
@RequestMapping("/api/earnings")
public class EarningsController {

    private final EarningsService earningsService;

    public EarningsController(EarningsService earningsService) {
        this.earningsService = earningsService;
    }

    // 📅 Week-wise earnings
    @GetMapping("/week")
    public WeekWiseEarningsResponseDTO getWeekly(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to) {

        Long riderId = getRiderIdFromToken();
        return earningsService.getWeeklyEarnings(riderId, from, to);
    }

    // 📦 Order earnings
    @GetMapping("/orders/{orderId}")
    public OrderEarningsDTO getOrderEarnings(
            @PathVariable String orderId) {

        Long riderId = getRiderIdFromToken();
        return earningsService.getOrderEarnings(riderId, orderId);
    }

    // 📆 Day-wise earnings
    @GetMapping("/{date}")
    public DayWiseEarningsDTO getDayWise(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        Long riderId = getRiderIdFromToken();
        return earningsService.getDayWiseEarnings(riderId, date);
    }

    // 🗓 Month-wise earnings
    @GetMapping("/month")
    public WeekWiseEarningsResponseDTO getMonthly(
            @RequestParam String month) {

        Long riderId = getRiderIdFromToken();
        return earningsService.getMonthlyEarnings(
                riderId, YearMonth.parse(month));
    }

    // 📊 Earnings summary
    @GetMapping("/summary")
    public EarningsSummaryDTO getSummary() {

        Long riderId = getRiderIdFromToken();
        return earningsService.getEarningsSummary(riderId);
    }

    // 🔐 Helper method (local to controller)
    private Long getRiderIdFromToken() {
        return (Long) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}

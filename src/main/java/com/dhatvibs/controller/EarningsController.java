package com.dhatvibs.controller;

import com.dhatvibs.dto.*;
import com.dhatvibs.service.EarningsService;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping("/week")
    public WeekWiseEarningsResponseDTO getWeekly(
            @RequestParam Long riderId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to) {

        return earningsService.getWeeklyEarnings(riderId, from, to);
    }

    @GetMapping("/orders/{orderId}")
    public OrderEarningsDTO getOrderEarnings(
            @RequestParam Long riderId,
            @PathVariable String orderId) {

        return earningsService.getOrderEarnings(riderId, orderId);
    }

    @GetMapping("/{date}")
    public DayWiseEarningsDTO getDayWise(
            @RequestParam Long riderId,
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return earningsService.getDayWiseEarnings(riderId, date);
    }

    @GetMapping("/month")
    public WeekWiseEarningsResponseDTO getMonthly(
            @RequestParam Long riderId,
            @RequestParam String month) {

        return earningsService.getMonthlyEarnings(
                riderId, YearMonth.parse(month));
    }

    @GetMapping("/summary")
    public EarningsSummaryDTO getSummary(
            @RequestParam Long riderId) {

        return earningsService.getEarningsSummary(riderId);
    }
}

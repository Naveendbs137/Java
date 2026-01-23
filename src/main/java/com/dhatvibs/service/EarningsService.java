package com.dhatvibs.service;

import com.dhatvibs.dto.*;

import java.time.LocalDate;
import java.time.YearMonth;

public interface EarningsService {

    WeekWiseEarningsResponseDTO getWeeklyEarnings(
            Long riderId, LocalDate from, LocalDate to);

    OrderEarningsDTO getOrderEarnings(Long riderId, String orderId);

    DayWiseEarningsDTO getDayWiseEarnings(Long riderId, LocalDate date);

    WeekWiseEarningsResponseDTO getMonthlyEarnings(
            Long riderId, YearMonth month);

    EarningsSummaryDTO getEarningsSummary(Long riderId);
}

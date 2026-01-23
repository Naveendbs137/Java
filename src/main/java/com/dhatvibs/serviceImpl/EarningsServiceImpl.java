package com.dhatvibs.serviceImpl;

import com.dhatvibs.dto.*;
import com.dhatvibs.entity.EarningsEntity;
import com.dhatvibs.mapper.EarningsMapper;
import com.dhatvibs.repository.EarningsRepository;
import com.dhatvibs.service.EarningsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EarningsServiceImpl implements EarningsService {

    private final EarningsRepository earningsRepository;
    private final EarningsMapper mapper;

    public EarningsServiceImpl(EarningsRepository earningsRepository,
                               EarningsMapper mapper) {
        this.earningsRepository = earningsRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderEarningsDTO getOrderEarnings(Long riderId, String orderId) {
        EarningsEntity e = earningsRepository
                .findByOrder_OrderIdAndRider_Id(orderId, riderId)
                .orElseThrow(() -> new RuntimeException("Order earnings not found"));

        return mapper.toOrderDTO(e);
    }

    @Override
    public DayWiseEarningsDTO getDayWiseEarnings(Long riderId, LocalDate date) {

        List<EarningsEntity> list =
                earningsRepository.findByRider_IdAndCompletedAtBetween(
                        riderId,
                        date.atStartOfDay(),
                        date.atTime(23, 59, 59)
                );

        DayWiseEarningsDTO dto = new DayWiseEarningsDTO();
        dto.date = date;
        dto.ordersCompleted = list.size();
        dto.orders = list.stream().map(mapper::toOrderDTO).toList();

        return dto;
    }

    @Override
    public WeekWiseEarningsResponseDTO getWeeklyEarnings(
            Long riderId, LocalDate from, LocalDate to) {

        List<EarningsEntity> list =
                earningsRepository.findByRider_IdAndCompletedAtBetween(
                        riderId,
                        from.atStartOfDay(),
                        to.atTime(23, 59, 59)
                );

        Map<LocalDate, List<EarningsEntity>> grouped =
                list.stream().collect(Collectors.groupingBy(
                        e -> e.getCompletedAt().toLocalDate()
                ));

        WeekWiseEarningsResponseDTO dto = new WeekWiseEarningsResponseDTO();
        dto.from = from;
        dto.to = to;
        dto.totalOrders = list.size();
        dto.totalEarnings = list.stream()
                .map(EarningsEntity::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        dto.days = grouped.entrySet().stream().map(entry -> {
            DayWiseEarningsDTO d = new DayWiseEarningsDTO();
            d.date = entry.getKey();
            d.ordersCompleted = entry.getValue().size();
            d.orders = entry.getValue().stream()
                    .map(mapper::toOrderDTO).toList();
            return d;
        }).toList();

        return dto;
    }

    @Override
    public WeekWiseEarningsResponseDTO getMonthlyEarnings(
            Long riderId, YearMonth month) {

        return getWeeklyEarnings(
                riderId,
                month.atDay(1),
                month.atEndOfMonth()
        );
    }

    @Override
    public EarningsSummaryDTO getEarningsSummary(Long riderId) {

        LocalDate today = LocalDate.now();
        YearMonth month = YearMonth.now();

        EarningsSummaryDTO summary = new EarningsSummaryDTO();

        DayWiseEarningsDTO todayData =
                getDayWiseEarnings(riderId, today);

        summary.today = new EarningsSummaryDTO.Period();
        summary.today.from = today;
        summary.today.to = today;
        summary.today.ordersCompleted = todayData.ordersCompleted;
        summary.today.totalEarnings = todayData.orders.stream()
                .map(o -> o.earnings.total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        WeekWiseEarningsResponseDTO weekData =
                getWeeklyEarnings(riderId,
                        today.with(DayOfWeek.MONDAY),
                        today.with(DayOfWeek.SUNDAY));

        summary.week = new EarningsSummaryDTO.Period();
        summary.week.from = weekData.from;
        summary.week.to = weekData.to;
        summary.week.ordersCompleted = weekData.totalOrders;
        summary.week.totalEarnings = weekData.totalEarnings;

        WeekWiseEarningsResponseDTO monthData =
                getMonthlyEarnings(riderId, month);

        summary.month = new EarningsSummaryDTO.Period();
        summary.month.from = month.atDay(1);
        summary.month.to = month.atEndOfMonth();
        summary.month.ordersCompleted = monthData.totalOrders;
        summary.month.totalEarnings = monthData.totalEarnings;

        return summary;
    }
}

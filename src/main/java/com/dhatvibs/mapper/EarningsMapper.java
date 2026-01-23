package com.dhatvibs.mapper;

import com.dhatvibs.dto.*;
import com.dhatvibs.entity.EarningsEntity;
import org.springframework.stereotype.Component;

@Component
public class EarningsMapper {

    public OrderEarningsDTO toOrderDTO(EarningsEntity e) {

        EarningsBreakdownDTO breakdown = new EarningsBreakdownDTO();
        breakdown.basePay = e.getBasePay();
        breakdown.distancePay = e.getDistancePay();
        breakdown.surgePay = e.getSurgePay();
        breakdown.tips = e.getTips();
        breakdown.total = e.getTotal();

        OrderEarningsDTO dto = new OrderEarningsDTO();
        dto.orderId = e.getOrder().getOrderId();
        dto.completedAt = e.getCompletedAt();
        dto.earnings = breakdown;

        return dto;
    }
}

package com.dhatvibs.dto;

import java.time.LocalDateTime;

public class OrderEarningsDTO {

    public String orderId;
    public LocalDateTime completedAt;
    public EarningsBreakdownDTO earnings;
}

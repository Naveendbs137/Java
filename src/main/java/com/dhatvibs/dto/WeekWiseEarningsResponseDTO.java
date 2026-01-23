package com.dhatvibs.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class WeekWiseEarningsResponseDTO {

    public LocalDate from;
    public LocalDate to;
    public int totalOrders;
    public BigDecimal totalEarnings;
    public List<DayWiseEarningsDTO> days;
}

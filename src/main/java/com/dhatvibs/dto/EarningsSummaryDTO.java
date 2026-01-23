package com.dhatvibs.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EarningsSummaryDTO {

    public Period today;
    public Period week;
    public Period month;

    public static class Period {
        public LocalDate from;
        public LocalDate to;
        public int ordersCompleted;
        public BigDecimal totalEarnings;
    }
}

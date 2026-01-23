package com.dhatvibs.dto;

import java.time.LocalDate;
import java.util.List;

public class DayWiseEarningsDTO {

    public LocalDate date;
    public int ordersCompleted;
    public List<OrderEarningsDTO> orders;
}

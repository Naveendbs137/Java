package com.dhatvibs.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rider_incentive_progress")
public class RiderIncentiveProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long riderId;

    @ManyToOne
    @JoinColumn(name = "incentive_id")
    private Incentive incentive;

    private Integer completedOrders = 0;
    private Integer earnedAmount = 0;
    private Boolean achieved = false;

    private LocalDate date;       // DAILY
    private LocalDate weekStart;  // WEEKLY
    private LocalDate weekEnd;

    /* GETTERS */

    public Long getRiderId() { return riderId; }
    public Integer getCompletedOrders() { return completedOrders; }
    public Integer getEarnedAmount() { return earnedAmount; }
    public Boolean getAchieved() { return achieved; }
}

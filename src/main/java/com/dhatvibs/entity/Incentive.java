package com.dhatvibs.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "incentives")
public class Incentive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private IncentiveType incentiveType;

    @Enumerated(EnumType.STRING)
    private RewardType rewardType;

    private Integer rewardValue;
    private Integer maxRewardPerRider;

    /* CONDITIONS */
    private Integer minOrders;
    private LocalTime startTime;
    private LocalTime endTime;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private IncentiveStatus status = IncentiveStatus.ACTIVE;

    /* ===== GETTERS & SETTERS ===== */

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public IncentiveType getIncentiveType() { return incentiveType; }
    public void setIncentiveType(IncentiveType incentiveType) {
        this.incentiveType = incentiveType;
    }

    public RewardType getRewardType() { return rewardType; }
    public void setRewardType(RewardType rewardType) {
        this.rewardType = rewardType;
    }

    public Integer getRewardValue() { return rewardValue; }
    public void setRewardValue(Integer rewardValue) {
        this.rewardValue = rewardValue;
    }

    public Integer getMaxRewardPerRider() { return maxRewardPerRider; }
    public void setMaxRewardPerRider(Integer maxRewardPerRider) {
        this.maxRewardPerRider = maxRewardPerRider;
    }

    public Integer getMinOrders() { return minOrders; }
    public void setMinOrders(Integer minOrders) {
        this.minOrders = minOrders;
    }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public IncentiveStatus getStatus() { return status; }
    public void setStatus(IncentiveStatus status) {
        this.status = status;
    }
}

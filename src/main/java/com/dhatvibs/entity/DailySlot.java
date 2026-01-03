package com.dhatvibs.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "daily_slots")
public class DailySlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "weekly_slot_id")
    private WeeklySlot weeklySlot;

    private String slotKey;
    private LocalDate date;
    private String dayOfWeek;
    private int dayNumber;

    private LocalTime startTime;
    private LocalTime endTime;
    private int durationInMinutes;

    private LocalDateTime slotStartAt;
    private LocalDateTime slotEndAt;

    private int maxRiders;
    private int bookedRiders;

    private boolean isAvailable = true;
    private boolean isVisible = true;
    private boolean isLocked = false;
    private boolean autoLocked = false;

    private boolean isPeakSlot;
    private String incentiveText;
    private double incentiveAmount;

    private String status;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public WeeklySlot getWeeklySlot() { return weeklySlot; }
    public void setWeeklySlot(WeeklySlot weeklySlot) { this.weeklySlot = weeklySlot; }

    public String getSlotKey() { return slotKey; }
    public void setSlotKey(String slotKey) { this.slotKey = slotKey; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public int getDayNumber() { return dayNumber; }
    public void setDayNumber(int dayNumber) { this.dayNumber = dayNumber; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public int getDurationInMinutes() { return durationInMinutes; }
    public void setDurationInMinutes(int durationInMinutes) { this.durationInMinutes = durationInMinutes; }

    public LocalDateTime getSlotStartAt() { return slotStartAt; }
    public void setSlotStartAt(LocalDateTime slotStartAt) { this.slotStartAt = slotStartAt; }

    public LocalDateTime getSlotEndAt() { return slotEndAt; }
    public void setSlotEndAt(LocalDateTime slotEndAt) { this.slotEndAt = slotEndAt; }

    public int getMaxRiders() { return maxRiders; }
    public void setMaxRiders(int maxRiders) { this.maxRiders = maxRiders; }

    public int getBookedRiders() { return bookedRiders; }
    public void setBookedRiders(int bookedRiders) { this.bookedRiders = bookedRiders; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public boolean isVisible() { return isVisible; }
    public void setVisible(boolean visible) { isVisible = visible; }

    public boolean isLocked() { return isLocked; }
    public void setLocked(boolean locked) { isLocked = locked; }

    public boolean isAutoLocked() { return autoLocked; }
    public void setAutoLocked(boolean autoLocked) { this.autoLocked = autoLocked; }

    public boolean isPeakSlot() { return isPeakSlot; }
    public void setPeakSlot(boolean peakSlot) { isPeakSlot = peakSlot; }

    public String getIncentiveText() { return incentiveText; }
    public void setIncentiveText(String incentiveText) { this.incentiveText = incentiveText; }

    public double getIncentiveAmount() { return incentiveAmount; }
    public void setIncentiveAmount(double incentiveAmount) { this.incentiveAmount = incentiveAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

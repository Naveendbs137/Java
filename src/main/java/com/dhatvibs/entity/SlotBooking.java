package com.dhatvibs.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(
    name = "slot_bookings",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"rider_id", "date", "daily_slot_id"}
    )
)
public class SlotBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long riderId;

    @ManyToOne
    @JoinColumn(name = "daily_slot_id")
    private DailySlot dailySlot;

    private LocalDate date;
    private String dayOfWeek;
    private int dayNumber;
    private int weekNumber;
    private int year;

    private String city;
    private String zone;

    private String startTime;
    private String endTime;

    private LocalDateTime slotStartAt;
    private LocalDateTime slotEndAt;
    private int totalMinutes;

    private boolean isPeakSlot;
    private String incentiveText;

    @Enumerated(EnumType.STRING)
    private SlotBookingStatus status;

    private String bookedFrom;
    private String cancellationReason;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // getters & setters (ALL)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRiderId() { return riderId; }
    public void setRiderId(Long riderId) { this.riderId = riderId; }

    public DailySlot getDailySlot() { return dailySlot; }
    public void setDailySlot(DailySlot dailySlot) { this.dailySlot = dailySlot; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public SlotBookingStatus getStatus() { return status; }
    public void setStatus(SlotBookingStatus status) { this.status = status; }

    public String getBookedFrom() { return bookedFrom; }
    public void setBookedFrom(String bookedFrom) { this.bookedFrom = bookedFrom; }

    public String getCancellationReason() { return cancellationReason; }
    public void setCancellationReason(String cancellationReason) { this.cancellationReason = cancellationReason; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}

package com.dhatvibs.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(
    name = "weekly_slots",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"week_number", "year", "city", "zone"}
    )
)
public class WeeklySlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int weekNumber;
    private int year;
    private String city;
    private String zone;

    private boolean isDeleted = false;

    @OneToMany(mappedBy = "weeklySlot", cascade = CascadeType.ALL)
    private List<DailySlot> slots;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getWeekNumber() { return weekNumber; }
    public void setWeekNumber(int weekNumber) { this.weekNumber = weekNumber; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }

    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }

    public List<DailySlot> getSlots() { return slots; }
    public void setSlots(List<DailySlot> slots) { this.slots = slots; }
}

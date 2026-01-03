package com.dhatvibs.repository;

import com.dhatvibs.entity.WeeklySlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeeklySlotRepository extends JpaRepository<WeeklySlot, Long> {
    Optional<WeeklySlot> findByWeekNumberAndYearAndCityAndZone(
        int week, int year, String city, String zone
    );
}

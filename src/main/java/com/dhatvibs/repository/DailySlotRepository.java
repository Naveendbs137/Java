package com.dhatvibs.repository;

import com.dhatvibs.entity.DailySlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailySlotRepository extends JpaRepository<DailySlot, Long> {
    List<DailySlot> findByDateAndIsVisibleTrue(LocalDate date);
}

package com.dhatvibs.repository;

import com.dhatvibs.entity.RiderIncentiveProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RiderIncentiveProgressRepository
        extends JpaRepository<RiderIncentiveProgress, Long> {

    Optional<RiderIncentiveProgress>
    findByRiderIdAndIncentiveIdAndDate(
            Long riderId,
            Long incentiveId,
            LocalDate date
    );

    Optional<RiderIncentiveProgress>
    findByRiderIdAndIncentiveIdAndWeekStart(
            Long riderId,
            Long incentiveId,
            LocalDate weekStart
    );
}

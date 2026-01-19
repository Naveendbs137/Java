package com.dhatvibs.repository;

import com.dhatvibs.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IncentiveRepository extends JpaRepository<Incentive, Long> {

    List<Incentive> findByIncentiveTypeAndStatus(
            IncentiveType type,
            IncentiveStatus status
    );

    List<Incentive> findByIncentiveTypeAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            IncentiveType type,
            IncentiveStatus status,
            LocalDate now1,
            LocalDate now2
    );
}

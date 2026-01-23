package com.dhatvibs.repository;

import com.dhatvibs.entity.EarningsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EarningsRepository extends JpaRepository<EarningsEntity, Long> {

    Optional<EarningsEntity> findByOrder_OrderIdAndRider_Id(
            String orderId, Long riderId);

    List<EarningsEntity> findByRider_IdAndCompletedAtBetween(
            Long riderId,
            LocalDateTime start,
            LocalDateTime end
    );
}

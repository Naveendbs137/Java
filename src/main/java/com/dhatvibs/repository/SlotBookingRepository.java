package com.dhatvibs.repository;

import com.dhatvibs.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.*;

public interface SlotBookingRepository extends JpaRepository<SlotBooking, Long> {

    Optional<SlotBooking> findByRiderIdAndStatus(
        Long riderId, SlotBookingStatus status
    );

    List<SlotBooking> findByRiderIdOrderByCreatedAtDesc(Long riderId);
}

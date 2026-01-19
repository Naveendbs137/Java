package com.dhatvibs.repository;

import com.dhatvibs.entity.KitDeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KitDeliveryAddressRepository
        extends JpaRepository<KitDeliveryAddress, Long> {

    Optional<KitDeliveryAddress> findByRiderId(Long riderId);
}

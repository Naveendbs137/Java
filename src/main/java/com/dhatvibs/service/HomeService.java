package com.dhatvibs.service;

import com.dhatvibs.entity.Rider;
import com.dhatvibs.repository.RiderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HomeService {

    private final RiderRepository riderRepository;

    public HomeService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public Rider updateOnlineStatus(Long riderId, Boolean online) {
        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));

        rider.setIsOnline(online);
        rider.setUpdatedAt(LocalDateTime.now());

        return riderRepository.save(rider);
    }
}

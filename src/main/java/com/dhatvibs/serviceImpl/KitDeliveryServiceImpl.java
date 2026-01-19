package com.dhatvibs.serviceImpl;

import com.dhatvibs.dto.KitAddressRequestDto;
import com.dhatvibs.entity.KitDeliveryAddress;
import com.dhatvibs.entity.Rider;
import com.dhatvibs.repository.KitDeliveryAddressRepository;
import com.dhatvibs.repository.RiderRepository;
import com.dhatvibs.service.KitDeliveryService;
import org.springframework.stereotype.Service;

@Service
public class KitDeliveryServiceImpl implements KitDeliveryService {

    private final RiderRepository riderRepo;
    private final KitDeliveryAddressRepository kitRepo;

    public KitDeliveryServiceImpl(RiderRepository riderRepo,
                                  KitDeliveryAddressRepository kitRepo) {
        this.riderRepo = riderRepo;
        this.kitRepo = kitRepo;
    }

    @Override
    public KitDeliveryAddress saveAddress(Long riderId, KitAddressRequestDto dto) {

        Rider rider = riderRepo.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));

        KitDeliveryAddress address = kitRepo
                .findByRiderId(riderId)
                .orElse(new KitDeliveryAddress());

        address.setName(dto.name);
        address.setCompleteAddress(dto.completeAddress);
        address.setPincode(dto.pincode);
        address.setRider(rider);

        return kitRepo.save(address);
    }

    @Override
    public KitDeliveryAddress getAddress(Long riderId) {
        return kitRepo.findByRiderId(riderId)
                .orElseThrow(() -> new RuntimeException("Kit address not found"));
    }
}

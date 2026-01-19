package com.dhatvibs.service;

import com.dhatvibs.dto.KitAddressRequestDto;
import com.dhatvibs.entity.KitDeliveryAddress;

public interface KitDeliveryService {

    KitDeliveryAddress saveAddress(Long riderId, KitAddressRequestDto dto);

    KitDeliveryAddress getAddress(Long riderId);
}

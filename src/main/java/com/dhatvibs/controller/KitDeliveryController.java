/*
 * package com.dhatvibs.controller;
 * 
 * import com.dhatvibs.dto.KitAddressRequestDto; import
 * com.dhatvibs.entity.KitDeliveryAddress; import
 * com.dhatvibs.service.KitDeliveryService; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import java.util.Map;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/rider/kit-address") public class KitDeliveryController
 * {
 * 
 * private final KitDeliveryService service;
 * 
 * public KitDeliveryController(KitDeliveryService service) { this.service =
 * service; }
 * 
 * @PostMapping public Map<String, Object> save(
 * 
 * @RequestParam Long riderId,
 * 
 * @RequestBody KitAddressRequestDto dto) {
 * 
 * KitDeliveryAddress address = service.saveAddress(riderId, dto);
 * 
 * return Map.of( "message", "Kit delivery address saved successfully", "data",
 * address ); }
 * 
 * @GetMapping public Map<String, Object> get(@RequestParam Long riderId) {
 * 
 * KitDeliveryAddress address = service.getAddress(riderId);
 * 
 * return Map.of( "message", "Kit delivery address fetched successfully",
 * "data", address ); } }
 */ 

package com.dhatvibs.controller;

import com.dhatvibs.dto.KitAddressRequestDto;
import com.dhatvibs.entity.KitDeliveryAddress;
import com.dhatvibs.service.KitDeliveryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/rider/kit-address")
public class KitDeliveryController {

    private final KitDeliveryService service;

    public KitDeliveryController(KitDeliveryService service) {
        this.service = service;
    }

    // 📦 Save kit delivery address
    @PostMapping
    public Map<String, Object> save(
            @RequestBody KitAddressRequestDto dto) {

        Long riderId = getRiderIdFromToken();

        KitDeliveryAddress address = service.saveAddress(riderId, dto);

        return Map.of(
                "message", "Kit delivery address saved successfully",
                "data", address
        );
    }

    // 📍 Get kit delivery address
    @GetMapping
    public Map<String, Object> get() {

        Long riderId = getRiderIdFromToken();

        KitDeliveryAddress address = service.getAddress(riderId);

        return Map.of(
                "message", "Kit delivery address fetched successfully",
                "data", address
        );
    }

    // 🔐 Helper method
    private Long getRiderIdFromToken() {
        return (Long) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}

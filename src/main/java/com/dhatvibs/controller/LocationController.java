package com.dhatvibs.controller;

import com.dhatvibs.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping("/cities")
    public ResponseEntity<Map<String, Object>> getCities() {

        List<String> cities = service.getCities();

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "cities", cities
                )
        );
    }

    @GetMapping("/areas")
    public ResponseEntity<Map<String, Object>> getAreas(
            @RequestParam String city
    ) {

        List<String> areas = service.getAreasByCity(city);

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "city", city,
                        "areas", areas
                )
        );
    }
}

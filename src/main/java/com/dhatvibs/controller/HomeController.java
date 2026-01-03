package com.dhatvibs.controller;

import com.dhatvibs.entity.Rider;
import com.dhatvibs.service.HomeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Dashboard loaded from DB logic";
    }

    @PostMapping("/rider/status")
    public Rider updateStatus(
            @RequestParam Long riderId,
            @RequestParam Boolean online) {
        return homeService.updateOnlineStatus(riderId, online);
    }

    @PostMapping("/rider/location/permission")
    public String updateLocationPermission() {
        return "Location permission saved";
    }
}

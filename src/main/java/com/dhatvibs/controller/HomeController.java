/*
 * package com.dhatvibs.controller;
 * 
 * import com.dhatvibs.entity.Rider; import com.dhatvibs.service.HomeService;
 * import org.springframework.web.bind.annotation.*;
 * 
 * @RestController
 * 
 * @RequestMapping("/home") public class HomeController {
 * 
 * private final HomeService homeService;
 * 
 * public HomeController(HomeService homeService) { this.homeService =
 * homeService; }
 * 
 * @GetMapping("/dashboard") public String dashboard() { return
 * "Dashboard loaded from DB logic"; }
 * 
 * @PostMapping("/rider/status") public Rider updateStatus(
 * 
 * @RequestParam Long riderId,
 * 
 * @RequestParam Boolean online) { return
 * homeService.updateOnlineStatus(riderId, online); }
 * 
 * @PostMapping("/rider/location/permission") public String
 * updateLocationPermission() { return "Location permission saved"; } }
 */ 


package com.dhatvibs.controller;

import com.dhatvibs.entity.Rider;
import com.dhatvibs.service.HomeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    // 🏠 Dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "Dashboard loaded from DB logic";
    }

    // 🔄 Update rider online/offline status
    @PostMapping("/rider/status")
    public Rider updateStatus(
            @RequestParam Boolean online) {

        Long riderId = getRiderIdFromToken();
        return homeService.updateOnlineStatus(riderId, online);
    }

    // 📍 Location permission
    @PostMapping("/rider/location/permission")
    public String updateLocationPermission() {
        return "Location permission saved";
    }

    // 🔐 Helper method
    private Long getRiderIdFromToken() {
        return (Long) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}

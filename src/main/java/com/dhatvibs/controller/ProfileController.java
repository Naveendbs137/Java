package com.dhatvibs.controller;

import com.dhatvibs.entity.Rider;
import com.dhatvibs.repository.RiderRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final RiderRepository riderRepository;

    public ProfileController(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    // 1. Profile Home
    @GetMapping
    public Rider profile(@RequestParam Long riderId) {
        return riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));
    }

    // 2. Upload Photo
    @PutMapping("/photo")
    public String uploadPhoto(@RequestParam MultipartFile file) {
        return "Photo uploaded (Azure logic here)";
    }

    // 3. Personal Info
    @GetMapping("/personal")
    public String personal(@RequestParam Long riderId) {
        return riderRepository.findById(riderId)
                .map(Rider::getFullName)
                .orElse("No data");
    }

    // 4. Vehicle
    @GetMapping("/vehicle")
    public String vehicle(@RequestParam Long riderId) {
        return riderRepository.findById(riderId)
                .map(Rider::getVehicleType)
                .orElse("No vehicle");
    }

    // 5. Insurance
    @GetMapping("/insurance")
    public String insurance(@RequestParam Long riderId) {
        return riderRepository.findById(riderId)
                .map(Rider::getInsuranceUrl)
                .orElse("No insurance");
    }

    // 6. Contract
    @GetMapping("/contract")
    public String contract() {
        return "Contract accepted";
    }

    // 7. Assets
    @GetMapping("/assets")
    public String assets() {
        return "Helmet, Bag";
    }

    // 8. Rewards
    @GetMapping("/rewards")
    public String rewards() {
        return "Rewards fetched from DB";
    }

    // 9. Order history
    @GetMapping("/order-history")
    public String orders() {
        return "Orders fetched from DB";
    }

    // 10. Slot history
    @GetMapping("/slot-history")
    public String slots() {
        return "Slots fetched from DB";
    }
}

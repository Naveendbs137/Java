package com.dhatvibs.controller;

import com.dhatvibs.entity.Rider;
import com.dhatvibs.repository.RiderRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final RiderRepository riderRepository;

    public BankController(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    // 1. Get bank
    @GetMapping
    public Rider getBank(@RequestParam Long riderId) {
        return riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));
    }

    // 2. Add bank
    @PostMapping
    public Rider addBank(@RequestParam Long riderId,
                         @RequestParam String bankName) {

        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));

        rider.setBankName(bankName);
        rider.setAddedBankAccount(true);

        return riderRepository.save(rider);
    }

    // 3. Update bank
    @PutMapping
    public Rider updateBank(@RequestParam Long riderId,
                            @RequestParam String bankName) {
        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));
        rider.setBankName(bankName);
        return riderRepository.save(rider);
    }

    // 4. Status
    @GetMapping("/status")
    public Boolean status(@RequestParam Long riderId) {
        return riderRepository.findById(riderId)
                .map(Rider::getAddedBankAccount)
                .orElse(false);
    }
}

/*
 * package com.dhatvibs.controller;
 * 
 * import com.dhatvibs.dto.BankDto; import com.dhatvibs.entity.Rider; import
 * com.dhatvibs.repository.RiderRepository; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import java.util.Map;
 * 
 * @RestController
 * 
 * @RequestMapping("/bank") public class BankController {
 * 
 * private final RiderRepository riderRepository;
 * 
 * public BankController(RiderRepository riderRepository) { this.riderRepository
 * = riderRepository; }
 * 
 * // 1️⃣ Get bank details
 * 
 * @GetMapping public ResponseEntity<Rider> getBank(@RequestParam Long riderId)
 * {
 * 
 * Rider rider = riderRepository.findById(riderId) .orElseThrow(() -> new
 * RuntimeException("Rider not found"));
 * 
 * return ResponseEntity.ok(rider); }
 * 
 * // 2️⃣ Add bank
 * 
 * @PostMapping public ResponseEntity<Map<String, String>> addBank(
 * 
 * @RequestParam Long riderId,
 * 
 * @RequestBody BankDto dto) {
 * 
 * Rider rider = riderRepository.findById(riderId) .orElseThrow(() -> new
 * RuntimeException("Rider not found"));
 * 
 * rider.setBankName(dto.bankName);
 * rider.setAccountHolderName(dto.accountHolderName);
 * rider.setAccountType(dto.accountType); rider.setBranch(dto.branch);
 * rider.setAccountNumber(dto.accountNumber); rider.setIfscCode(dto.ifscCode);
 * 
 * rider.setAddedBankAccount(true);
 * 
 * riderRepository.save(rider);
 * 
 * return ResponseEntity.ok( Map.of("message",
 * "Bank details added successfully") ); }
 * 
 * // 3️⃣ Update bank
 * 
 * @PutMapping public ResponseEntity<Map<String, String>> updateBank(
 * 
 * @RequestParam Long riderId,
 * 
 * @RequestBody BankDto dto) {
 * 
 * Rider rider = riderRepository.findById(riderId) .orElseThrow(() -> new
 * RuntimeException("Rider not found"));
 * 
 * rider.setBankName(dto.bankName);
 * rider.setAccountHolderName(dto.accountHolderName);
 * rider.setAccountType(dto.accountType); rider.setBranch(dto.branch);
 * rider.setAccountNumber(dto.accountNumber); rider.setIfscCode(dto.ifscCode);
 * 
 * riderRepository.save(rider);
 * 
 * return ResponseEntity.ok( Map.of("message",
 * "Bank details updated successfully") ); }
 * 
 * // 4️⃣ Bank status
 * 
 * @GetMapping("/status") public ResponseEntity<Map<String, Boolean>>
 * status(@RequestParam Long riderId) {
 * 
 * Boolean status = riderRepository.findById(riderId)
 * .map(Rider::getAddedBankAccount) .orElse(false);
 * 
 * return ResponseEntity.ok( Map.of("bankAdded", status) ); } }
 */ 

package com.dhatvibs.controller;

import com.dhatvibs.dto.BankDto;
import com.dhatvibs.entity.Rider;
import com.dhatvibs.repository.RiderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final RiderRepository riderRepository;

    public BankController(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    // 1️⃣ Get bank details (PROTECTED)
    @GetMapping
    public ResponseEntity<Rider> getBank(Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();

        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));

        return ResponseEntity.ok(rider);
    }

    // 2️⃣ Add bank (PROTECTED)
    @PostMapping
    public ResponseEntity<Map<String, String>> addBank(
            @RequestBody BankDto dto,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();

        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));

        rider.setBankName(dto.bankName);
        rider.setAccountHolderName(dto.accountHolderName);
        rider.setAccountType(dto.accountType);
        rider.setBranch(dto.branch);
        rider.setAccountNumber(dto.accountNumber);
        rider.setIfscCode(dto.ifscCode);
        rider.setAddedBankAccount(true);

        riderRepository.save(rider);

        return ResponseEntity.ok(
                Map.of("message", "Bank details added successfully")
        );
    }

    // 3️⃣ Update bank (PROTECTED)
    @PutMapping
    public ResponseEntity<Map<String, String>> updateBank(
            @RequestBody BankDto dto,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();

        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));

        rider.setBankName(dto.bankName);
        rider.setAccountHolderName(dto.accountHolderName);
        rider.setAccountType(dto.accountType);
        rider.setBranch(dto.branch);
        rider.setAccountNumber(dto.accountNumber);
        rider.setIfscCode(dto.ifscCode);

        riderRepository.save(rider);

        return ResponseEntity.ok(
                Map.of("message", "Bank details updated successfully")
        );
    }

    // 4️⃣ Bank status (PROTECTED)
    @GetMapping("/status")
    public ResponseEntity<Map<String, Boolean>> status(
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();

        Boolean status = riderRepository.findById(riderId)
                .map(Rider::getAddedBankAccount)
                .orElse(false);

        return ResponseEntity.ok(
                Map.of("bankAdded", status)
        );
    }
}

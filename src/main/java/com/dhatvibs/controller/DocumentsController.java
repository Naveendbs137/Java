package com.dhatvibs.controller;

import com.dhatvibs.entity.Rider;
import com.dhatvibs.repository.RiderRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentsController {

    private final RiderRepository riderRepository;

    public DocumentsController(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    // 1. Get documents
    @GetMapping
    public Rider getDocuments(@RequestParam Long riderId) {
        return riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));
    }

    // 2. Upload document
    @PostMapping("/upload")
    public String upload(@RequestParam Long riderId,
                         @RequestParam String type,
                         @RequestParam MultipartFile file) {

        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));

        if ("PAN".equals(type)) rider.setPanUrl("azure-url");
        if ("DL".equals(type)) rider.setDlUrl("azure-url");
        if ("INSURANCE".equals(type)) rider.setInsuranceUrl("azure-url");

        riderRepository.save(rider);
        return "Uploaded";
    }

    // 3. Get by type
    @GetMapping("/{type}")
    public String getByType(@PathVariable String type) {
        return "Fetched " + type;
    }

    // 4. Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return "Deleted document " + id;
    }
}

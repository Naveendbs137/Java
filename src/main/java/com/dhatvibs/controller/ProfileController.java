/*
 * package com.dhatvibs.controller;
 * 
 * import com.dhatvibs.entity.Rider; import
 * com.dhatvibs.repository.RiderRepository; import
 * org.springframework.web.bind.annotation.*; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * @RestController
 * 
 * @RequestMapping("/profile") public class ProfileController {
 * 
 * private final RiderRepository riderRepository;
 * 
 * public ProfileController(RiderRepository riderRepository) {
 * this.riderRepository = riderRepository; }
 * 
 * // 1. Profile Home
 * 
 * @GetMapping public Rider profile(@RequestParam Long riderId) { return
 * riderRepository.findById(riderId) .orElseThrow(() -> new
 * RuntimeException("Rider not found")); }
 * 
 * // 2. Upload Photo
 * 
 * @PutMapping("/photo") public String uploadPhoto(@RequestParam MultipartFile
 * file) { return "Photo uploaded (Azure logic here)"; }
 * 
 * // 3. Personal Info
 * 
 * @GetMapping("/personal") public String personal(@RequestParam Long riderId) {
 * return riderRepository.findById(riderId) .map(Rider::getFullName)
 * .orElse("No data"); }
 * 
 * // 4. Vehicle
 * 
 * @GetMapping("/vehicle") public String vehicle(@RequestParam Long riderId) {
 * return riderRepository.findById(riderId) .map(Rider::getVehicleType)
 * .orElse("No vehicle"); }
 * 
 * // 5. Insurance
 * 
 * @GetMapping("/insurance") public String insurance(@RequestParam Long riderId)
 * { return riderRepository.findById(riderId) .map(Rider::getInsuranceUrl)
 * .orElse("No insurance"); }
 * 
 * // 6. Contract
 * 
 * @GetMapping("/contract") public String contract() { return
 * "Contract accepted"; }
 * 
 * // 7. Assets
 * 
 * @GetMapping("/assets") public String assets() { return "Helmet, Bag"; }
 * 
 * // 8. Rewards
 * 
 * @GetMapping("/rewards") public String rewards() { return
 * "Rewards fetched from DB"; }
 * 
 * // 9. Order history
 * 
 * @GetMapping("/order-history") public String orders() { return
 * "Orders fetched from DB"; }
 * 
 * // 10. Slot history
 * 
 * @GetMapping("/slot-history") public String slots() { return
 * "Slots fetched from DB"; } }
 */ 

/*
 * package com.dhatvibs.controller;
 * 
 * import com.dhatvibs.entity.Rider; import
 * com.dhatvibs.repository.RiderRepository; import
 * jakarta.servlet.http.HttpServletRequest; import
 * org.springframework.web.bind.annotation.*; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * import java.util.Map;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/profile") public class ProfileController {
 * 
 * private final RiderRepository riderRepository;
 * 
 * public ProfileController(RiderRepository riderRepository) {
 * this.riderRepository = riderRepository; }
 * 
 * private Long getRiderId(HttpServletRequest request) { return (Long)
 * request.getAttribute("riderId"); }
 * 
 * // 1️⃣ Profile Home
 * 
 * @GetMapping public Map<String, Object> profile(HttpServletRequest request) {
 * 
 * Long riderId = getRiderId(request);
 * 
 * Rider rider = riderRepository.findById(riderId) .orElseThrow(() -> new
 * RuntimeException("Rider not found"));
 * 
 * return Map.of( "success", true, "message", "Profile fetched successfully",
 * "data", rider ); }
 * 
 * // 2️⃣ Upload Photo
 * 
 * @PutMapping("/photo") public Map<String, Object> uploadPhoto(
 * 
 * @RequestParam MultipartFile file, HttpServletRequest request) {
 * 
 * Long riderId = getRiderId(request);
 * 
 * // upload logic here using riderId
 * 
 * return Map.of( "success", true, "message",
 * "Profile photo uploaded successfully" ); }
 * 
 * // 3️⃣ Personal Info
 * 
 * @GetMapping("/personal") public Map<String, Object>
 * personal(HttpServletRequest request) {
 * 
 * Long riderId = getRiderId(request);
 * 
 * Rider rider = riderRepository.findById(riderId) .orElseThrow(() -> new
 * RuntimeException("Rider not found"));
 * 
 * return Map.of( "success", true, "message", "Personal info fetched", "data",
 * Map.of( "fullName", rider.getFullName(), "email", rider.getEmail() ) ); }
 * 
 * // 4️⃣ Vehicle
 * 
 * @GetMapping("/vehicle") public Map<String, Object> vehicle(HttpServletRequest
 * request) {
 * 
 * Long riderId = getRiderId(request);
 * 
 * String vehicle = riderRepository.findById(riderId)
 * .map(Rider::getVehicleType) .orElse(null);
 * 
 * return Map.of( "success", true, "message", "Vehicle details fetched", "data",
 * Map.of("vehicleType", vehicle) ); }
 * 
 * // 5️⃣ Insurance
 * 
 * @GetMapping("/insurance") public Map<String, Object>
 * insurance(HttpServletRequest request) {
 * 
 * Long riderId = getRiderId(request);
 * 
 * String insuranceUrl = riderRepository.findById(riderId)
 * .map(Rider::getInsuranceUrl) .orElse(null);
 * 
 * return Map.of( "success", true, "message", "Insurance details fetched",
 * "data", Map.of("insuranceUrl", insuranceUrl) ); }
 * 
 * // 6️⃣ Contract
 * 
 * @GetMapping("/contract") public Map<String, Object> contract() { return
 * Map.of( "success", true, "message", "Contract accepted" ); }
 * 
 * // 7️⃣ Assets
 * 
 * @GetMapping("/assets") public Map<String, Object> assets() { return Map.of(
 * "success", true, "message", "Assets fetched", "data", new String[]{"Helmet",
 * "Bag"} ); }
 * 
 * // 8️⃣ Rewards
 * 
 * @GetMapping("/rewards") public Map<String, Object> rewards() { return Map.of(
 * "success", true, "message", "Rewards fetched", "data", "Rewards data" ); }
 * 
 * // 9️⃣ Order history
 * 
 * @GetMapping("/order-history") public Map<String, Object> orders() { return
 * Map.of( "success", true, "message", "Order history fetched" ); }
 * 
 * // 🔟 Slot history
 * 
 * @GetMapping("/slot-history") public Map<String, Object> slots() { return
 * Map.of( "success", true, "message", "Slot history fetched" ); } }
 */  

package com.dhatvibs.controller;

import com.dhatvibs.entity.Rider;
import com.dhatvibs.repository.RiderRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final RiderRepository riderRepository;

    public ProfileController(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    // 🔹 COMMON METHOD: get logged-in rider
    private Rider getLoggedInRider() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String phoneNumber = auth.getName(); // comes from JWT

        return riderRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Rider not found"));
    }

    // 1️⃣ Profile Home
    @GetMapping
    public Rider profile() {
        return getLoggedInRider();
    }

    // 2️⃣ Upload Photo
    @PutMapping("/photo")
    public String uploadPhoto(@RequestParam MultipartFile file) {
        Rider rider = getLoggedInRider();
        return "Photo uploaded for rider " + rider.getId();
    }

    // 3️⃣ Personal Info
    @GetMapping("/personal")
    public String personal() {
        return getLoggedInRider().getFullName();
    }

    // 4️⃣ Vehicle
    @GetMapping("/vehicle")
    public String vehicle() {
        return getLoggedInRider().getVehicleType();
    }

    // 5️⃣ Insurance
    @GetMapping("/insurance")
    public String insurance() {
        return getLoggedInRider().getInsuranceUrl();
    }

    // 6️⃣ Contract
    @GetMapping("/contract")
    public String contract() {
        return "Contract accepted";
    }

    // 7️⃣ Assets
    @GetMapping("/assets")
    public String assets() {
        return "Helmet, Bag";
    }

    // 8️⃣ Rewards
    @GetMapping("/rewards")
    public String rewards() {
        return "Rewards fetched from DB";
    }

    // 9️⃣ Order history
    @GetMapping("/order-history")
    public String orders() {
        return "Orders fetched from DB";
    }

    // 🔟 Slot history
    @GetMapping("/slot-history")
    public String slots() {
        return "Slots fetched from DB";
    }
}

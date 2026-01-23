/*
 * package com.dhatvibs.controller;
 * 
 * import com.dhatvibs.dto.*; import com.dhatvibs.service.OnboardingService;
 * 
 * import java.util.Map;
 * 
 * import org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.*; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/onboarding") public class OnboardingController {
 * 
 * private final OnboardingService service;
 * 
 * public OnboardingController(OnboardingService s) { this.service = s; }
 * 
 * 
 * @PostMapping("/send-otp") public void sendOtp(@RequestBody SendOtpDto dto) {
 * service.sendOtp(dto); }
 * 
 * 
 * @PostMapping("/send-otp") public ResponseEntity<Map<String, String>>
 * sendOtp(@RequestBody SendOtpDto dto) { service.sendOtp(dto); return
 * ResponseEntity.ok( Map.of("message", "OTP sent successfully") ); }
 * 
 * 
 * @PostMapping("/verify-otp") public TokenResponseDto verifyOtp(@RequestBody
 * VerifyOtpDto dto) { return service.verifyOtp(dto); }
 * 
 * @PostMapping("/permissions") public void
 * permissions(@RequestHeader("X-RIDER-ID") Long id,
 * 
 * @RequestBody PermissionDto dto) { service.savePermissions(id, dto); }
 * 
 * @PostMapping("/location") public void location(@RequestHeader("X-RIDER-ID")
 * Long id,
 * 
 * @RequestBody LocationDto dto) { service.saveLocation(id, dto); }
 * 
 * @PostMapping("/vehicle") public void vehicle(@RequestHeader("X-RIDER-ID")
 * Long id,
 * 
 * @RequestBody VehicleDto dto) { service.saveVehicle(id, dto); }
 * 
 * @PostMapping("/personal-info") public void
 * personalInfo(@RequestHeader("X-RIDER-ID") Long id,
 * 
 * @RequestBody PersonalInfoDto dto) { service.savePersonalInfo(id, dto); }
 * 
 * @PostMapping(value = "/selfie", consumes = "multipart/form-data") public void
 * selfie(@RequestHeader("X-RIDER-ID") Long id,
 * 
 * @RequestPart MultipartFile file) { service.uploadSelfie(id, file); }
 * 
 * @PostMapping(value = "/pan", consumes = "multipart/form-data") public void
 * pan(@RequestHeader("X-RIDER-ID") Long id,
 * 
 * @RequestPart MultipartFile file) { service.uploadPan(id, file); }
 * 
 * @PostMapping(value = "/dl", consumes = "multipart/form-data") public void
 * dl(@RequestHeader("X-RIDER-ID") Long id,
 * 
 * @RequestPart MultipartFile front,
 * 
 * @RequestPart MultipartFile back) { service.uploadDl(id, front, back); } }
 */





/*
 * package com.dhatvibs.controller;
 * 
 * import com.dhatvibs.dto.*; import com.dhatvibs.service.OnboardingService;
 * 
 * import java.util.Map;
 * 
 * import org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.*; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/onboarding") public class OnboardingController {
 * 
 * private final OnboardingService service;
 * 
 * public OnboardingController(OnboardingService service) { this.service =
 * service; }
 * 
 * ================= OTP =================
 * 
 * @PostMapping("/send-otp") public ResponseEntity<Map<String, String>>
 * sendOtp(@RequestBody SendOtpDto dto) { service.sendOtp(dto); return
 * ResponseEntity.ok( Map.of("message", "OTP sent successfully") ); }
 * 
 * @PostMapping("/verify-otp") public ResponseEntity<TokenResponseDto>
 * verifyOtp(@RequestBody VerifyOtpDto dto) { return
 * ResponseEntity.ok(service.verifyOtp(dto)); }
 * 
 * ================= PERMISSIONS =================
 * 
 * @PostMapping("/permissions") public ResponseEntity<Map<String, String>>
 * permissions(
 * 
 * @RequestHeader("X-RIDER-ID") Long id,
 * 
 * @RequestBody PermissionDto dto) {
 * 
 * service.savePermissions(id, dto); return ResponseEntity.ok( Map.of("message",
 * "Permissions saved successfully") ); }
 * 
 * ================= LOCATION =================
 * 
 * @PostMapping("/location") public ResponseEntity<Map<String, String>>
 * location(
 * 
 * @RequestHeader("X-RIDER-ID") Long id,
 * 
 * @RequestBody LocationDto dto) {
 * 
 * service.saveLocation(id, dto); return ResponseEntity.ok( Map.of("message",
 * "Location saved successfully") ); }
 * 
 * ================= VEHICLE =================
 * 
 * @PostMapping("/vehicle") public ResponseEntity<Map<String, String>> vehicle(
 * 
 * @RequestHeader("X-RIDER-ID") Long id,
 * 
 * @RequestBody VehicleDto dto) {
 * 
 * service.saveVehicle(id, dto); return ResponseEntity.ok( Map.of("message",
 * "Vehicle details saved successfully") ); }
 * 
 * ================= PERSONAL INFO =================
 * 
 * @PostMapping("/personal-info") public ResponseEntity<Map<String, String>>
 * personalInfo(
 * 
 * @RequestHeader("X-RIDER-ID") Long id,
 * 
 * @RequestBody PersonalInfoDto dto) {
 * 
 * service.savePersonalInfo(id, dto); return ResponseEntity.ok(
 * Map.of("message", "Personal information saved successfully") ); }
 * 
 * ================= DOCUMENT UPLOADS =================
 * 
 * @PostMapping(value = "/selfie", consumes = "multipart/form-data") public
 * ResponseEntity<Map<String, String>> selfie(
 * 
 * @RequestHeader("X-RIDER-ID") Long id,
 * 
 * @RequestPart MultipartFile file) {
 * 
 * service.uploadSelfie(id, file); return ResponseEntity.ok( Map.of("message",
 * "Selfie uploaded successfully") ); }
 * 
 * 
 * 
 * ===========adhar verification using send otp=========
 * 
 * @PostMapping("/aadhaar/send-otp") public ResponseEntity<Map<String, String>>
 * sendAadhaarOtp(
 * 
 * @RequestBody SendAadhaarOtpDto dto) {
 * 
 * service.sendAadhaarOtp(dto.getAadhaarNumber());
 * 
 * return ResponseEntity.ok( Map.of("message", "Aadhaar OTP sent successfully")
 * ); }
 * 
 * @PostMapping("/aadhaar/verify-otp") public ResponseEntity<Map<String,
 * String>> verifyAadhaarOtp(
 * 
 * @RequestBody VerifyAadhaarOtpDto dto) {
 * 
 * service.verifyAadhaarOtp(dto.getAadhaarNumber(), dto.getOtp());
 * 
 * return ResponseEntity.ok( Map.of("message", "Aadhaar verified successfully")
 * ); }
 * 
 * 
 * 
 * 
 * @PostMapping(value = "/pan", consumes = "multipart/form-data") public
 * ResponseEntity<Map<String, String>> pan(
 * 
 * @RequestHeader("X-RIDER-ID") Long id,
 * 
 * @RequestPart MultipartFile file) {
 * 
 * service.uploadPan(id, file); return ResponseEntity.ok( Map.of("message",
 * "PAN uploaded successfully") ); }
 * 
 * @PostMapping(value = "/dl", consumes = "multipart/form-data") public
 * ResponseEntity<Map<String, String>> dl(
 * 
 * @RequestHeader("X-RIDER-ID") Long id,
 * 
 * @RequestPart MultipartFile front,
 * 
 * @RequestPart MultipartFile back) {
 * 
 * service.uploadDl(id, front, back); return ResponseEntity.ok(
 * Map.of("message", "Driving license uploaded successfully") ); } }
 */




package com.dhatvibs.controller;

import com.dhatvibs.dto.*;
import com.dhatvibs.service.OnboardingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    private final OnboardingService service;

    public OnboardingController(OnboardingService service) {
        this.service = service;
    }

    /* ================= OTP (PUBLIC) ================= */

    @PostMapping("/send-otp")
    public ResponseEntity<Map<String, String>> sendOtp(
            @RequestBody SendOtpDto dto) {

        service.sendOtp(dto);
        return ResponseEntity.ok(
                Map.of("message", "OTP sent successfully")
        );
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<TokenResponseDto> verifyOtp(
            @RequestBody VerifyOtpDto dto) {

        return ResponseEntity.ok(service.verifyOtp(dto));
    }

    /* ================= PERMISSIONS (PROTECTED) ================= */

    @PostMapping("/permissions")
    public ResponseEntity<Map<String, String>> permissions(
            @RequestBody PermissionDto dto,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();
        service.savePermissions(riderId, dto);

        return ResponseEntity.ok(
                Map.of("message", "Permissions saved successfully")
        );
    }

    /* ================= LOCATION (PROTECTED) ================= */

    @PostMapping("/location")
    public ResponseEntity<Map<String, String>> location(
            @RequestBody LocationDto dto,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();
        service.saveLocation(riderId, dto);

        return ResponseEntity.ok(
                Map.of("message", "Location saved successfully")
        );
    }

    /* ================= VEHICLE (PROTECTED) ================= */

    @PostMapping("/vehicle")
    public ResponseEntity<Map<String, String>> vehicle(
            @RequestBody VehicleDto dto,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();
        service.saveVehicle(riderId, dto);

        return ResponseEntity.ok(
                Map.of("message", "Vehicle details saved successfully")
        );
    }

    /* ================= PERSONAL INFO (PROTECTED) ================= */

    @PostMapping("/personal-info")
    public ResponseEntity<Map<String, String>> personalInfo(
            @RequestBody PersonalInfoDto dto,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();
        service.savePersonalInfo(riderId, dto);

        return ResponseEntity.ok(
                Map.of("message", "Personal information saved successfully")
        );
    }

    /* ================= SELFIE UPLOAD (PROTECTED) ================= */

    @PostMapping(value = "/selfie", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, String>> selfie(
            @RequestPart MultipartFile file,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();
        service.uploadSelfie(riderId, file);

        return ResponseEntity.ok(
                Map.of("message", "Selfie uploaded successfully")
        );
    }

    /* ================= AADHAAR OTP (DECIDE BASED ON FLOW) ================= */

    @PostMapping("/aadhaar/send-otp")
    public ResponseEntity<Map<String, String>> sendAadhaarOtp(
            @RequestBody SendAadhaarOtpDto dto) {

        service.sendAadhaarOtp(dto.getAadhaarNumber());

        return ResponseEntity.ok(
                Map.of("message", "Aadhaar OTP sent successfully")
        );
    }

    @PostMapping("/aadhaar/verify-otp")
    public ResponseEntity<Map<String, String>> verifyAadhaarOtp(
            @RequestBody VerifyAadhaarOtpDto dto) {

        service.verifyAadhaarOtp(
                dto.getAadhaarNumber(),
                dto.getOtp()
        );

        return ResponseEntity.ok(
                Map.of("message", "Aadhaar verified successfully")
        );
    }

    /* ================= PAN (PROTECTED) ================= */

    @PostMapping(value = "/pan", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, String>> pan(
            @RequestPart MultipartFile file,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();
        service.uploadPan(riderId, file);

        return ResponseEntity.ok(
                Map.of("message", "PAN uploaded successfully")
        );
    }

    /* ================= DL (PROTECTED) ================= */

    @PostMapping(value = "/dl", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, String>> dl(
            @RequestPart MultipartFile front,
            @RequestPart MultipartFile back,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();
        service.uploadDl(riderId, front, back);

        return ResponseEntity.ok(
                Map.of("message", "Driving license uploaded successfully")
        );
    }
}

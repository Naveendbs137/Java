

package com.dhatvibs.controller;

import com.dhatvibs.dto.*;
import com.dhatvibs.service.OnboardingService;
import com.dhatvibs.serviceImpl.OnboardingServiceImpl;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    private final OnboardingServiceImpl onboardingServiceImpl;

    private final OnboardingService service;

    public OnboardingController(OnboardingService service, OnboardingServiceImpl onboardingServiceImpl) {
        this.service = service;
        this.onboardingServiceImpl = onboardingServiceImpl;
    }

    /* ================= OTP (PUBLIC) ================= */

	/*
	 * @PostMapping("/send-otp") public ResponseEntity<Map<String, String>> sendOtp(
	 * 
	 * @RequestBody SendOtpDto dto) {
	 * 
	 * service.sendOtp(dto); return ResponseEntity.ok( Map.of("message",
	 * "OTP sent successfully") ); }
	 */
    
    @PostMapping("/send-otp")
    public ResponseEntity<Map<String, String>> sendOtp(
            @Valid @RequestBody SendOtpDto dto) {

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

	/*
	 * @PostMapping("/aadhaar/send-otp") public ResponseEntity<Map<String, String>>
	 * sendAadhaarOtp(
	 * 
	 * @RequestBody SendAadhaarOtpDto dto) {
	 * 
	 * service.sendAadhaarOtp(dto.getAadhaarNumber());
	 * 
	 * return ResponseEntity.ok( Map.of("message", "Aadhaar OTP sent successfully")
	 * ); }
	 */ 
    
    

	/*
	 * @PostMapping("/aadhaar/verify-otp") public ResponseEntity<Map<String,
	 * String>> verifyAadhaarOtp(
	 * 
	 * @RequestBody VerifyAadhaarOtpDto dto) {
	 * 
	 * service.verifyAadhaarOtp( dto.getAadhaarNumber(), dto.getOtp() );
	 * 
	 * return ResponseEntity.ok( Map.of("message", "Aadhaar verified successfully")
	 * ); }
	 */  
    
    
    @PostMapping("/aadhaar/send-otp")
    public ResponseEntity<Map<String, String>> sendAadhaarOtp(
            @RequestBody SendAadhaarOtpDto dto,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();
        service.sendAadhaarOtp(riderId, dto.getAadhaarNumber());

        return ResponseEntity.ok(
                Map.of("message", "Aadhaar OTP sent successfully")
        );
    }

    @PostMapping("/aadhaar/verify-otp")
    public ResponseEntity<Map<String, String>> verifyAadhaarOtp(
            @RequestBody VerifyAadhaarOtpDto dto,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();
        service.verifyAadhaarOtp(riderId, dto.getOtp());

        return ResponseEntity.ok(
                Map.of("message", "Aadhaar verified successfully")
        );
    }


    /* ================= PAN (PROTECTED) ================= */

	/*
	 * @PostMapping(value = "/pan", consumes = "multipart/form-data") public
	 * ResponseEntity<Map<String, String>> pan(
	 * 
	 * @RequestPart MultipartFile file, Authentication authentication) {
	 * 
	 * Long riderId = (Long) authentication.getPrincipal();
	 * service.uploadPan(riderId, file);
	 * 
	 * return ResponseEntity.ok( Map.of("message", "PAN uploaded successfully") ); }
	 */ 
    
    @PostMapping(value = "/pan", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, String>> pan(
            @RequestPart("panNumber") String panNumber,
            @RequestPart("file") MultipartFile file,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();
        service.uploadPan(riderId, panNumber, file);

        return ResponseEntity.ok(
                Map.of("message", "PAN uploaded successfully")
        );
    }


    /* ================= DL (PROTECTED) ================= */

	/*
	 * @PostMapping(value = "/dl", consumes = "multipart/form-data") public
	 * ResponseEntity<Map<String, String>> dl(
	 * 
	 * @RequestPart MultipartFile front,
	 * 
	 * @RequestPart MultipartFile back, Authentication authentication) {
	 * 
	 * Long riderId = (Long) authentication.getPrincipal();
	 * service.uploadDl(riderId, front, back);
	 * 
	 * return ResponseEntity.ok( Map.of("message",
	 * "Driving license uploaded successfully") ); }
	 */
    @PostMapping(value = "/dl", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, String>> dl(
            @RequestPart("dlNumber") String dlNumber,
            @RequestPart("front") MultipartFile front,
            @RequestPart("back") MultipartFile back,
            Authentication authentication) {

        Long riderId = (Long) authentication.getPrincipal();
        service.uploadDl(riderId, dlNumber, front, back);

        return ResponseEntity.ok(
                Map.of("message", "Driving License uploaded successfully")
        );
    }
    
    
    
	/*
	 * @GetMapping("/onboarding-status") public OnboardingStatusResponseDto
	 * onboardingStatus( Authentication authentication ) { Long riderId = (Long)
	 * authentication.getPrincipal(); return
	 * onboardingService.getOnboardingStatus(riderId); }
	 */ 
    
    @GetMapping("/onboarding-status")
    public OnboardingStatusResponseDto onboardingStatus(
            Authentication authentication
    ) {
        Long riderId = (Long) authentication.getPrincipal();
        return service.getOnboardingStatus(riderId);
    }



    @RestController
    @RequestMapping("/api/rider")
    public class RiderController {

        private final OnboardingService onboardingService;

        public RiderController(OnboardingService onboardingService) {
            this.onboardingService = onboardingService;
        }

        @PostMapping("/complete-kyc")
        public ResponseEntity<KycCompleteResponseDto> completeKyc(
                Authentication authentication
        ) {
            Long riderId = (Long) authentication.getPrincipal();

            KycCompleteResponseDto response =
                    onboardingService.completeKyc(riderId);

            if (!response.isSuccess()) {
                return ResponseEntity.badRequest().body(response);
            }

            return ResponseEntity.ok(response);
        }
    }

    
    
    
}

package com.dhatvibs.service;

import com.dhatvibs.dto.*;
import org.springframework.web.multipart.MultipartFile;

public interface OnboardingService {

    void sendOtp(SendOtpDto dto);
    TokenResponseDto verifyOtp(VerifyOtpDto dto);

    void savePermissions(Long riderId, PermissionDto dto);
    void saveLocation(Long riderId, LocationDto dto);
    void saveVehicle(Long riderId, VehicleDto dto);
    void savePersonalInfo(Long riderId, PersonalInfoDto dto);

    void uploadSelfie(Long riderId, MultipartFile file); 
    //aadhar verification
    void sendAadhaarOtp(String aadhaarNumber);
    void verifyAadhaarOtp(String aadhaarNumber, String otp);

    
    void uploadPan(Long riderId,String panNumber, MultipartFile file);
    void uploadDl(Long riderId,String dlNumber, MultipartFile front, MultipartFile back);  
    
    OnboardingStatusResponseDto getOnboardingStatus(Long riderId); 
    
    KycCompleteResponseDto completeKyc(Long riderId);


}

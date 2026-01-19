package com.dhatvibs.serviceImpl;

import com.dhatvibs.dto.*;
import com.dhatvibs.entity.*;
import com.dhatvibs.repository.RiderRepository;
import com.dhatvibs.service.OnboardingService;
import com.dhatvibs.util.AzureUploadUtil;
import com.dhatvibs.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class OnboardingServiceImpl implements OnboardingService {

    private final RiderRepository repo;
    private final JwtUtil jwt;
    private final AzureUploadUtil azure;

    public OnboardingServiceImpl(RiderRepository r, JwtUtil j, AzureUploadUtil a) {
        this.repo = r;
        this.jwt = j;
        this.azure = a;
    }

    @Override
    public void sendOtp(SendOtpDto dto) {
        Rider r = repo.findByPhoneNumber(dto.getPhoneNumber()).orElse(new Rider());
        r.setPhoneNumber(dto.getPhoneNumber());
        r.setOtpCode("007007");
        r.setOtpExpiresAt(LocalDateTime.now().plusMinutes(5));
        repo.save(r);
    }

    @Override
    public TokenResponseDto verifyOtp(VerifyOtpDto dto) {
        Rider r = repo.findByPhoneNumber(dto.getPhoneNumber()).orElseThrow();

        if (!"007007".equals(dto.getOtp()))
            throw new RuntimeException("Invalid OTP");

        r.setPhoneVerified(true);
        r.setLastOtpVerifiedAt(LocalDateTime.now());
        r.setOnboardingStage(OnboardingStage.APP_PERMISSIONS);

        String at = jwt.generateAccessToken(r.getId());
        String rt = jwt.generateRefreshToken(r.getId());

        r.setRefreshToken(rt);
        repo.save(r);

        return new TokenResponseDto(at, rt);
    }

    @Override
    public void savePermissions(Long id, PermissionDto d) {
        Rider r = repo.findById(id).orElseThrow();
        r.setCameraPermission(d.camera);
        r.setForegroundLocation(d.foregroundLocation);
        r.setBackgroundLocation(d.backgroundLocation);
        r.setOnboardingStage(OnboardingStage.SELECT_LOCATION);
        repo.save(r);
    }

    @Override
    public void saveLocation(Long id, LocationDto d) {
        Rider r = repo.findById(id).orElseThrow();
        r.setCity(d.city);
        r.setState(d.state);
        r.setPincode(d.pincode);
        r.setOnboardingStage(OnboardingStage.SELECT_VEHICLE);
        repo.save(r);
    }

    @Override
    public void saveVehicle(Long id, VehicleDto d) {
        Rider r = repo.findById(id).orElseThrow();
        r.setVehicleType(d.vehicleType);
        r.setOnboardingStage(OnboardingStage.PERSONAL_INFO);
        repo.save(r);
    }

    @Override
    public void savePersonalInfo(Long id, PersonalInfoDto d) {
        Rider r = repo.findById(id).orElseThrow();
        r.setFullName(d.fullName);
        r.setEmail(d.email);
        r.setOnboardingStage(OnboardingStage.SELFIE);
        repo.save(r);
    }

    @Override
    public void uploadSelfie(Long id, MultipartFile f) {
        Rider r = repo.findById(id).orElseThrow();
        r.setSelfieUrl(azure.upload(f, "selfie"));
        r.setOnboardingStage(OnboardingStage.AADHAAR);
        repo.save(r);
    }
    
    
    @Override
    public void sendAadhaarOtp(String aadhaarNumber) {

        // Basic validation
        if (aadhaarNumber == null || aadhaarNumber.length() != 12) {
            throw new RuntimeException("Invalid Aadhaar number");
        }

        // MOCK OTP FLOW
        // Real integration later: UIDAI / Digilocker
        System.out.println("Aadhaar OTP sent: 007007");
    }

    @Override
    public void verifyAadhaarOtp(String aadhaarNumber, String otp) {

        if (!"007007".equals(otp)) {
            throw new RuntimeException("Invalid Aadhaar OTP");
        }

        // Mark Aadhaar as verified (DB update later)
        System.out.println("Aadhaar verified successfully");
    }

    
    
    
    
    @Override
    public void uploadPan(Long id, MultipartFile f) {
        Rider r = repo.findById(id).orElseThrow();
        r.setPanImageUrl(azure.upload(f, "pan"));
        r.setOnboardingStage(OnboardingStage.DL_UPLOAD);
        repo.save(r);
    }

    @Override
    public void uploadDl(Long id, MultipartFile f, MultipartFile b) {
        Rider r = repo.findById(id).orElseThrow();
        r.setDlFrontImage(azure.upload(f, "dl"));
        r.setDlBackImage(azure.upload(b, "dl"));
        r.setOnboardingStage(OnboardingStage.COMPLETED);
        r.setIsFullyRegistered(true);
        repo.save(r);
    }
}

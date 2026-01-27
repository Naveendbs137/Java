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
       // r.setState(d.state);
        //r.setPincode(d.pincode);
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

    
    
    
    
	/*
	 * @Override public void uploadPan(Long id, MultipartFile f) { Rider r =
	 * repo.findById(id).orElseThrow(); r.setPanImageUrl(azure.upload(f, "pan"));
	 * r.setOnboardingStage(OnboardingStage.DL_UPLOAD); repo.save(r); }
	 */ 
    
    @Override
    public void uploadPan(Long id, String panNumber, MultipartFile file) {

        Rider r = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rider not found"));

        // ✅ PAN format validation (India)
        if (panNumber == null || !panNumber.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
            throw new RuntimeException("Invalid PAN number");
        }

        // Upload PAN image
        String panImageUrl = azure.upload(file, "pan");

        // Save PAN details
        r.setPanNumber(panNumber);
        r.setPanImageUrl(panImageUrl);
        r.setPanStatus("submitted"); // or "pending"
        r.setPanRejectionReason(null);

        // Move onboarding forward
        r.setOnboardingStage(OnboardingStage.DL_UPLOAD);

        repo.save(r);
    }


	/*
	 * @Override public void uploadDl(Long id, MultipartFile f, MultipartFile b) {
	 * Rider r = repo.findById(id).orElseThrow(); r.setDlFrontImage(azure.upload(f,
	 * "dl")); r.setDlBackImage(azure.upload(b, "dl"));
	 * r.setOnboardingStage(OnboardingStage.COMPLETED);
	 * r.setIsFullyRegistered(true); repo.save(r); }
	 */ 
    @Override
    public void uploadDl(Long id, String dlNumber, MultipartFile front, MultipartFile back) {

        Rider r = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Rider not found"));

        // ✅ Basic DL number validation (India – simple)
        if (dlNumber == null || dlNumber.length() < 10) {
            throw new RuntimeException("Invalid Driving License number");
        }

        String frontUrl = azure.upload(front, "dl");
        String backUrl  = azure.upload(back, "dl");

        r.setDlNumber(dlNumber);
        r.setDlFrontImage(frontUrl);
        r.setDlBackImage(backUrl);
        r.setDlStatus("submitted");
        r.setDlRejectionReason(null);

        r.setOnboardingStage(OnboardingStage.COMPLETED);
        r.setIsFullyRegistered(true);

        repo.save(r);
    } 
    
    
    
    @Override
    public OnboardingStatusResponseDto getOnboardingStatus(Long riderId) {

        Rider r = repo.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));

        // ---------- Progress ----------
        boolean appPermissionDone =
                Boolean.TRUE.equals(r.getCameraPermission()) &&
                Boolean.TRUE.equals(r.getForegroundLocation()) &&
                Boolean.TRUE.equals(r.getBackgroundLocation());

        boolean personalInfoDone =
                r.getFullName() != null &&
                r.getEmail() != null;

        boolean selfieDone = r.getSelfieUrl() != null;

        boolean panUploaded = r.getPanImageUrl() != null;

        boolean dlUploaded =
                r.getDlFrontImage() != null &&
                r.getDlBackImage() != null;

        boolean kycCompleted =
                Boolean.TRUE.equals(r.getAadhaarVerified()) &&
                panUploaded &&
                dlUploaded;

        OnboardingProgressDto progress = OnboardingProgressDto.builder()
                .phoneVerified(r.getPhoneVerified())
                .appPermissionDone(appPermissionDone)
                .citySelected(r.getCity() != null)
                .vehicleSelected(r.getVehicleType() != null)
                .personalInfoSubmitted(personalInfoDone)
                .selfieUploaded(selfieDone)
                .aadharVerified(r.getAadhaarVerified())
                .panUploaded(panUploaded)
                .dlUploaded(dlUploaded)
                .kycCompleted(kycCompleted)
                .build();

        // ---------- Current Stage ----------
        OnboardingStage stage = determineStage(progress);

        boolean fullyRegistered = stage == OnboardingStage.COMPLETED;

        // Sync DB (important)
        r.setOnboardingStage(stage);
        r.setIsFullyRegistered(fullyRegistered);
        repo.save(r);

        return OnboardingStatusResponseDto.builder()
                .success(true)
                .message("Onboarding status fetched successfully")
                .onboardingStage(stage)
                .onboardingProgress(progress)
                .isFullyRegistered(fullyRegistered)
                .build();
    }

    
    private OnboardingStage determineStage(OnboardingProgressDto p) {

        if (!p.getPhoneVerified())
            return OnboardingStage.PHONE_VERIFICATION;

        if (!p.getAppPermissionDone())
            return OnboardingStage.APP_PERMISSIONS;

        if (!p.getCitySelected())
            return OnboardingStage.SELECT_LOCATION;

        if (!p.getVehicleSelected())
            return OnboardingStage.SELECT_VEHICLE;

        if (!p.getPersonalInfoSubmitted())
            return OnboardingStage.PERSONAL_INFO;

        if (!p.getSelfieUploaded())
            return OnboardingStage.SELFIE;

        if (!p.getAadharVerified())
            return OnboardingStage.AADHAAR;

        if (!p.getPanUploaded())
            return OnboardingStage.PAN_UPLOAD;

        if (!p.getDlUploaded())
            return OnboardingStage.DL_UPLOAD;

        if (!p.getKycCompleted())
            return OnboardingStage.KYC_SUBMITTED;

        return OnboardingStage.COMPLETED;
    } 
    
    
    
    @Override
    public KycCompleteResponseDto completeKyc(Long riderId) {

        Rider r = repo.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found"));

        // ✅ KYC checks
        boolean aadhaarDone = Boolean.TRUE.equals(r.getAadhaarVerified());
        boolean panDone = r.getPanImageUrl() != null;
        boolean dlDone =
                r.getDlFrontImage() != null &&
                r.getDlBackImage() != null;

        if (!(aadhaarDone && panDone && dlDone)) {
            return KycCompleteResponseDto.builder()
                    .success(false)
                    .message("Onboarding steps not completed")
                    .isFullyRegistered(false)
                    .build();
        }

        // ✅ Mark completed
        r.setOnboardingStage(OnboardingStage.COMPLETED);
        r.setIsFullyRegistered(true);
        repo.save(r);

        OnboardingProgressDto progress = OnboardingProgressDto.builder()
                .kycCompleted(true)
                .build();

        return KycCompleteResponseDto.builder()
                .success(true)
                .message("KYC completed and rider fully registered")
                .onboardingStage(OnboardingStage.COMPLETED)
                .onboardingProgress(progress)
                .isFullyRegistered(true)
                .build();
    }


    

}

/*
 * package com.dhatvibs.entity;
 * 
 * import jakarta.persistence.*; import java.time.LocalDateTime;
 * 
 * @Entity
 * 
 * @Table(name = "riders") public class Rider {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
 * 
 * PROFILE private String fullName; private String email; private String
 * vehicleType;
 * 
 * BANK private String bankName; private String accountHolderName; private
 * String accountNumber; private String ifscCode; private Boolean
 * addedBankAccount = false;
 * 
 * DOCUMENTS private String panUrl; private String dlUrl; private String
 * insuranceUrl;
 * 
 * AUDIT private LocalDateTime createdAt; private LocalDateTime updatedAt;
 * 
 * private Boolean isOnline;
 * 
 * ===== MANUAL GETTERS & SETTERS =====
 * 
 * public Long getId() { return id; } public void setId(Long id) { this.id = id;
 * }
 * 
 * public String getFullName() { return fullName; } public void
 * setFullName(String fullName) { this.fullName = fullName; }
 * 
 * public String getEmail() { return email; } public void setEmail(String email)
 * { this.email = email; }
 * 
 * public String getVehicleType() { return vehicleType; } public void
 * setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
 * 
 * public String getBankName() { return bankName; } public void
 * setBankName(String bankName) { this.bankName = bankName; }
 * 
 * public Boolean getAddedBankAccount() { return addedBankAccount; } public void
 * setAddedBankAccount(Boolean addedBankAccount) { this.addedBankAccount =
 * addedBankAccount; } public Boolean getIsOnline() { return isOnline; }
 * 
 * public void setIsOnline(Boolean isOnline) { this.isOnline = isOnline; }
 * 
 * public LocalDateTime getUpdatedAt() { return updatedAt; }
 * 
 * public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt =
 * updatedAt; } public String getPanUrl() { return panUrl; } public void
 * setPanUrl(String panUrl) { this.panUrl = panUrl; }
 * 
 * public String getDlUrl() { return dlUrl; } public void setDlUrl(String dlUrl)
 * { this.dlUrl = dlUrl; }
 * 
 * public String getInsuranceUrl() { return insuranceUrl; } public void
 * setInsuranceUrl(String insuranceUrl) { this.insuranceUrl = insuranceUrl; }
 * 
 * @PrePersist void onCreate() { createdAt = LocalDateTime.now(); updatedAt =
 * LocalDateTime.now(); }
 * 
 * @PreUpdate void onUpdate() { updatedAt = LocalDateTime.now(); } }
 */ 

package com.dhatvibs.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "riders")
public class Rider {

    /* ================= CORE ================= */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ================= PROFILE ================= */

    private String fullName;
    private String email;
    private String vehicleType;

    /* ================= BANK ================= */

    private String bankName;
    private String accountHolderName;
    private String accountNumber;
    private String ifscCode;
    private String accountType;
    private String branch;
    private Boolean addedBankAccount = false;

    /* ================= DOCUMENTS (OLD – KEEP) ================= */

    private String panUrl;
    private String dlUrl;
    private String insuranceUrl;
    
    
    /* ================= KIT DELIVERY ================= */

    @OneToOne(mappedBy = "rider", cascade = CascadeType.ALL)
    @JsonIgnore
    private KitDeliveryAddress kitDeliveryAddress;


    /* ================= ONBOARDING – NEW ================= */

    /* PHONE */
    private String countryCode = "+91";
    @Column(unique = true)
    private String phoneNumber;
    private Boolean phoneVerified = false;

    /* OTP */
    private String otpCode;
    private LocalDateTime otpExpiresAt;
    private LocalDateTime lastOtpVerifiedAt;

    /* TOKENS */
    @Column(length = 1000)
    private String refreshToken;

    /* ONBOARDING STATUS */
    @Enumerated(EnumType.STRING)
    private OnboardingStage onboardingStage = OnboardingStage.PHONE_VERIFICATION;

    private Boolean isFullyRegistered = false;

    /* APP PERMISSIONS */
    private Boolean cameraPermission = false;
    private Boolean foregroundLocation = false;
    private Boolean backgroundLocation = false;

    /* LOCATION */
    private String streetAddress;
    private String area;
    private String city;
    private String state;
    private String pincode;

    /* PERSONAL INFO */
    private LocalDate dob;
    private String gender;
    private String primaryPhone;
    private String secondaryPhone;

    /* SELFIE */
    private String selfieUrl;
    private LocalDateTime selfieUploadedAt;

    /* KYC – AADHAAR */
	/*
	 * private Boolean aadharVerified = false;
	 */ 
    
    /* ================= KYC – AADHAAR ================= */

    @Column(length = 12)
    private String aadhaarNumber; // store masked later (e.g. XXXX-XXXX-1234)

    private String aadhaarOtp;

    private LocalDateTime aadhaarOtpExpiresAt;

    private LocalDateTime aadhaarVerifiedAt;

    private Boolean aadhaarVerified = false;

    
    
    
    /* KYC – PAN */
    private String panNumber;
    private String panImageUrl;
    private String panStatus = "pending";
    private String panRejectionReason;

    /* KYC – DRIVING LICENSE */
    private String dlNumber;
    private String dlFrontImage;
    private String dlBackImage;
    private String dlStatus = "pending";
    private String dlRejectionReason;

    /* ================= RIDER STATUS ================= */

    private Boolean isOnline;

    /* ================= AUDIT ================= */

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public Boolean getAddedBankAccount() { return addedBankAccount; }
    public void setAddedBankAccount(Boolean addedBankAccount) {
        this.addedBankAccount = addedBankAccount;
    } 
    
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    

    public Boolean getIsOnline() { return isOnline; }
    public void setIsOnline(Boolean isOnline) { this.isOnline = isOnline; }

    public String getPanUrl() { return panUrl; }
    public void setPanUrl(String panUrl) { this.panUrl = panUrl; }

    public String getDlUrl() { return dlUrl; }
    public void setDlUrl(String dlUrl) { this.dlUrl = dlUrl; }

    public String getInsuranceUrl() { return insuranceUrl; }
    public void setInsuranceUrl(String insuranceUrl) { this.insuranceUrl = insuranceUrl; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Boolean getPhoneVerified() { return phoneVerified; }
    public void setPhoneVerified(Boolean phoneVerified) { this.phoneVerified = phoneVerified; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public OnboardingStage getOnboardingStage() { return onboardingStage; }
    public void setOnboardingStage(OnboardingStage onboardingStage) {
        this.onboardingStage = onboardingStage;
    } 
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    } 
    
    
    
    public KitDeliveryAddress getKitDeliveryAddress() {
        return kitDeliveryAddress;
    }

    public void setKitDeliveryAddress(KitDeliveryAddress kitDeliveryAddress) {
        this.kitDeliveryAddress = kitDeliveryAddress;
    }



    /* ================= JPA HOOKS ================= */

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
	public String getOtpCode() {
		return otpCode;
	}
	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}
	public LocalDateTime getOtpExpiresAt() {
		return otpExpiresAt;
	}
	public void setOtpExpiresAt(LocalDateTime otpExpiresAt) {
		this.otpExpiresAt = otpExpiresAt;
	}
	public Boolean getCameraPermission() {
		return cameraPermission;
	}
	public void setCameraPermission(Boolean cameraPermission) {
		this.cameraPermission = cameraPermission;
	}
	public Boolean getForegroundLocation() {
		return foregroundLocation;
	}
	public void setForegroundLocation(Boolean foregroundLocation) {
		this.foregroundLocation = foregroundLocation;
	}
	public Boolean getBackgroundLocation() {
		return backgroundLocation;
	}
	public void setBackgroundLocation(Boolean backgroundLocation) {
		this.backgroundLocation = backgroundLocation;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public LocalDateTime getLastOtpVerifiedAt() {
		return lastOtpVerifiedAt;
	}
	public void setLastOtpVerifiedAt(LocalDateTime lastOtpVerifiedAt) {
		this.lastOtpVerifiedAt = lastOtpVerifiedAt;
	}
	public Boolean getIsFullyRegistered() {
		return isFullyRegistered;
	}
	public void setIsFullyRegistered(Boolean isFullyRegistered) {
		this.isFullyRegistered = isFullyRegistered;
	}
	public String getSelfieUrl() {
		return selfieUrl;
	}
	public void setSelfieUrl(String selfieUrl) {
		this.selfieUrl = selfieUrl;
	}
	public String getPanImageUrl() {
		return panImageUrl;
	}
	public void setPanImageUrl(String panImageUrl) {
		this.panImageUrl = panImageUrl;
	}
	public String getDlFrontImage() {
		return dlFrontImage;
	}
	public void setDlFrontImage(String dlFrontImage) {
		this.dlFrontImage = dlFrontImage;
	}
	public String getDlBackImage() {
		return dlBackImage;
	}
	public void setDlBackImage(String dlBackImage) {
		this.dlBackImage = dlBackImage;
	} 
	/* ================= KYC – DRIVING LICENSE ================= */

	public String getDlNumber() {
	    return dlNumber;
	}

	public void setDlNumber(String dlNumber) {
	    this.dlNumber = dlNumber;
	}

	public String getDlStatus() {
	    return dlStatus;
	}

	public void setDlStatus(String dlStatus) {
	    this.dlStatus = dlStatus;
	}

	public String getDlRejectionReason() {
	    return dlRejectionReason;
	}

	public void setDlRejectionReason(String dlRejectionReason) {
	    this.dlRejectionReason = dlRejectionReason;
	}

	
	public String getAadhaarNumber() {
	    return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
	    this.aadhaarNumber = aadhaarNumber;
	}

	public String getAadhaarOtp() {
	    return aadhaarOtp;
	}

	public void setAadhaarOtp(String aadhaarOtp) {
	    this.aadhaarOtp = aadhaarOtp;
	}

	public LocalDateTime getAadhaarOtpExpiresAt() {
	    return aadhaarOtpExpiresAt;
	}

	public void setAadhaarOtpExpiresAt(LocalDateTime aadhaarOtpExpiresAt) {
	    this.aadhaarOtpExpiresAt = aadhaarOtpExpiresAt;
	}

	public Boolean getAadhaarVerified() {
	    return aadhaarVerified;
	}

	public void setAadhaarVerified(Boolean aadhaarVerified) {
	    this.aadhaarVerified = aadhaarVerified;
	}

	public LocalDateTime getAadhaarVerifiedAt() {
	    return aadhaarVerifiedAt;
	}

	public void setAadhaarVerifiedAt(LocalDateTime aadhaarVerifiedAt) {
	    this.aadhaarVerifiedAt = aadhaarVerifiedAt;
	} 
	
	
	/* ================= KYC – PAN ================= */

	public String getPanNumber() {
	    return panNumber;
	}

	public void setPanNumber(String panNumber) {
	    this.panNumber = panNumber;
	}

	public String getPanStatus() {
	    return panStatus;
	}

	public void setPanStatus(String panStatus) {
	    this.panStatus = panStatus;
	}

	public String getPanRejectionReason() {
	    return panRejectionReason;
	}

	public void setPanRejectionReason(String panRejectionReason) {
	    this.panRejectionReason = panRejectionReason;
	}


	
	
}

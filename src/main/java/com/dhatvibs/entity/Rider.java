package com.dhatvibs.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "riders")
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* PROFILE */
    private String fullName;
    private String email;
    private String vehicleType;

    /* BANK */
    private String bankName;
    private String accountHolderName;
    private String accountNumber;
    private String ifscCode;
    private Boolean addedBankAccount = false;

    /* DOCUMENTS */
    private String panUrl;
    private String dlUrl;
    private String insuranceUrl;

    /* AUDIT */
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

	private Boolean isOnline;

    /* ===== MANUAL GETTERS & SETTERS ===== */

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
    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getPanUrl() { return panUrl; }
    public void setPanUrl(String panUrl) { this.panUrl = panUrl; }

    public String getDlUrl() { return dlUrl; }
    public void setDlUrl(String dlUrl) { this.dlUrl = dlUrl; }

    public String getInsuranceUrl() { return insuranceUrl; }
    public void setInsuranceUrl(String insuranceUrl) { this.insuranceUrl = insuranceUrl; }

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

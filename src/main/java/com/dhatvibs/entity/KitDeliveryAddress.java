package com.dhatvibs.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class KitDeliveryAddress {
    private String name;
    private String mobileNumber;
    private String completeAddress;
    private String landmark;
    private String pincode;
    private Boolean onboardingKitStatus = false;
}

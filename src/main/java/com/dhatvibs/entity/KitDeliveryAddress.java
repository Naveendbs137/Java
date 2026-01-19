/*
 * package com.dhatvibs.entity;
 * 
 * import jakarta.persistence.Embeddable; import lombok.Getter; import
 * lombok.Setter;
 * 
 * @Embeddable
 * 
 * @Getter @Setter public class KitDeliveryAddress { private String name;
 * private String mobileNumber; private String completeAddress; private String
 * landmark; private String pincode; private Boolean onboardingKitStatus =
 * false; }
 */ 


package com.dhatvibs.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kit_delivery_addresses")
public class KitDeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 200)
    private String completeAddress;

    private String landmark;

    @Column(length = 6)
    private String pincode;

    @Column(length = 10)
    private String mobileNumber;

    private Boolean onboardingKitStatus = false;

    @OneToOne
    @JoinColumn(name = "rider_id", nullable = false)
    private Rider rider;

    /* Getters & Setters */

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCompleteAddress() { return completeAddress; }
    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public String getLandmark() { return landmark; }
    public void setLandmark(String landmark) { this.landmark = landmark; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Boolean getOnboardingKitStatus() { return onboardingKitStatus; }
    public void setOnboardingKitStatus(Boolean onboardingKitStatus) {
        this.onboardingKitStatus = onboardingKitStatus;
    }

    public Rider getRider() { return rider; }
    public void setRider(Rider rider) { this.rider = rider; }
}

package com.dhatvibs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Kyc {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "isVerified", column = @Column(name = "aadhar_is_verified")),
        @AttributeOverride(name = "status", column = @Column(name = "aadhar_status")),
        @AttributeOverride(name = "rejectionReason", column = @Column(name = "aadhar_rejection_reason"))
    })
    private Aadhar aadhar;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "number", column = @Column(name = "pan_number")),
        @AttributeOverride(name = "image", column = @Column(name = "pan_image")),
        @AttributeOverride(name = "status", column = @Column(name = "pan_status")),
        @AttributeOverride(name = "rejectionReason", column = @Column(name = "pan_rejection_reason"))
    })
    private Pan pan;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "number", column = @Column(name = "dl_number")),
        @AttributeOverride(name = "frontImage", column = @Column(name = "dl_front_image")),
        @AttributeOverride(name = "backImage", column = @Column(name = "dl_back_image")),
        @AttributeOverride(name = "status", column = @Column(name = "dl_status")),
        @AttributeOverride(name = "rejectionReason", column = @Column(name = "dl_rejection_reason"))
    })
    private DrivingLicense drivingLicense;
}

package com.dhatvibs.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class DrivingLicense {
    private String number;
    private String frontImage;
    private String backImage;
    private String status;
    private String rejectionReason;
}

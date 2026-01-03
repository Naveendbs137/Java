package com.dhatvibs.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Aadhar {
    private Boolean isVerified = false;
    private String status; // pending, approved, rejected
    private String rejectionReason;
}

package com.dhatvibs.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Pan {
    private String number;
    private String image;
    private String status;
    private String rejectionReason;
}

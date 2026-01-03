package com.dhatvibs.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Phone {
    private String countryCode = "+91";
    private String number;
    private Boolean isVerified = false;
}

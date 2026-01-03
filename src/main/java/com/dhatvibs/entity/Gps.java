package com.dhatvibs.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Gps {

    private Boolean isEnabled;

    @Embedded
    private GpsLocation lastLocation;
}

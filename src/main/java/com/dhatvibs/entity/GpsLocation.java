package com.dhatvibs.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class GpsLocation {
    private Double lat;
    private Double lng;
}

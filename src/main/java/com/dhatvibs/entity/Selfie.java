package com.dhatvibs.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Selfie {
    private String url;
    private LocalDateTime uploadedAt;
}

package com.dhatvibs.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class OnboardingProgress {
    private Boolean phoneVerified = false;
    private Boolean appPermissionDone = false;
    private Boolean citySelected = false;
    private Boolean vehicleSelected = false;
    private Boolean personalInfoSubmitted = false;
    private Boolean selfieUploaded = false;
    private Boolean aadharVerified = false;
    private Boolean panUploaded = false;
    private Boolean dlUploaded = false;
}

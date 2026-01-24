package com.dhatvibs.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OnboardingProgressDto {

    private Boolean phoneVerified;
    private Boolean appPermissionDone;
    private Boolean citySelected;
    private Boolean vehicleSelected;
    private Boolean personalInfoSubmitted;
    private Boolean selfieUploaded;
    private Boolean aadharVerified;
    private Boolean panUploaded;
    private Boolean dlUploaded;
    private Boolean kycCompleted;
}

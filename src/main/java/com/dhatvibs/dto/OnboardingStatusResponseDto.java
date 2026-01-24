package com.dhatvibs.dto;

import com.dhatvibs.entity.OnboardingStage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OnboardingStatusResponseDto {
    private Boolean success;
    private String message;
    private OnboardingStage onboardingStage;
    private OnboardingProgressDto onboardingProgress;
    private Boolean isFullyRegistered;
}

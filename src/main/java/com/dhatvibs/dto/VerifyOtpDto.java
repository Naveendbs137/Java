package com.dhatvibs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class VerifyOtpDto {
    //private String phone;
	@NotBlank
	@Pattern(regexp = "^[6-9]\\d{9}$")
	private String phone;

    private String otp;
    public String getPhone() { return phone; }
    public String getOtp() { return otp; }
}

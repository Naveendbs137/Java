/*
 * package com.dhatvibs.dto;
 * 
 * public class VerifyAadhaarOtpDto {
 * 
 * }
 */ 


package com.dhatvibs.dto;

import lombok.Data;

@Data
public class VerifyAadhaarOtpDto {
    private String aadhaarNumber;
    private String otp;
}

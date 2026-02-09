/*
 * package com.dhatvibs.dto;
 * 
 * public class SendOtpDto { private String phoneNumber; public String
 * getPhoneNumber() { return phoneNumber; } public void setPhoneNumber(String
 * phoneNumber) { this.phoneNumber = phoneNumber; } }
 */
package com.dhatvibs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SendOtpDto {

    @NotBlank(message = "Phone is required")
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Phone must be a valid 10-digit Indian number"
    )
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

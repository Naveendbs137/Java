package com.dhatvibs.entity;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class PersonalInfo {
    private String fullName;
    private LocalDate dob;
    private String gender;
    private String primaryPhone;
    private String secondaryPhone;
    private String email;
}

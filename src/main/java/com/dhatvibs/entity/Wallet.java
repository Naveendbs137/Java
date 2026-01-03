package com.dhatvibs.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Wallet {
    private Double balance = 0.0;
    private Double totalEarned = 0.0;
    private Double totalWithdrawn = 0.0;
}

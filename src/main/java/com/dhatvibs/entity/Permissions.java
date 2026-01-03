package com.dhatvibs.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Permissions {
    private Boolean camera = false;
    private Boolean foregroundLocation = false;
    private Boolean backgroundLocation = false;
}

package com.dhatvibs.entity;

import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class RiderStatus {

    private Boolean isOnline;
    private LocalDateTime lastOnlineAt;

    // ===== MANUAL GETTERS & SETTERS =====

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean online) {
        this.isOnline = online;
    }

    public LocalDateTime getLastOnlineAt() {
        return lastOnlineAt;
    }

    public void setLastOnlineAt(LocalDateTime lastOnlineAt) {
        this.lastOnlineAt = lastOnlineAt;
    }
}

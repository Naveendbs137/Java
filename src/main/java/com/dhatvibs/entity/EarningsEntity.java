package com.dhatvibs.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "earnings")
public class EarningsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK → Rider
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rider_id", nullable = false)
    private Rider rider;

    // FK → Order
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private BigDecimal basePay;
    private BigDecimal distancePay;
    private BigDecimal surgePay;
    private BigDecimal tips;
    private BigDecimal total;

    private LocalDateTime completedAt;

    /* Getters & Setters */

    public Long getId() {
        return id;
    }

    public Rider getRider() {
        return rider;
    }

    public Order getOrder() {
        return order;
    }

    public BigDecimal getBasePay() {
        return basePay;
    }

    public BigDecimal getDistancePay() {
        return distancePay;
    }

    public BigDecimal getSurgePay() {
        return surgePay;
    }

    public BigDecimal getTips() {
        return tips;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setBasePay(BigDecimal basePay) {
        this.basePay = basePay;
    }

    public void setDistancePay(BigDecimal distancePay) {
        this.distancePay = distancePay;
    }

    public void setSurgePay(BigDecimal surgePay) {
        this.surgePay = surgePay;
    }

    public void setTips(BigDecimal tips) {
        this.tips = tips;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}

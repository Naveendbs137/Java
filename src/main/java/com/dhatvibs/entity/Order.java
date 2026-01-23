package com.dhatvibs.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String orderId;

	/*
	 * private Long riderId;
	 */   
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "rider_id",
        referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "fk_order_rider")
    )
    private Rider rider;

    
    private String vendorShopName;
	 
	 

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> items;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus = OrderStatus.CREATED;


    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    private Double totalAmount;
    private Double riderEarning;

    @Embedded
    private DeliveryOtp deliveryOtp;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /* ===== MANUAL GETTERS & SETTERS ===== */

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

	/*
	 * public Long getRiderId() { return riderId; } public void setRiderId(Long
	 * riderId) { this.riderId = riderId; }
	 */ 
    
    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    
    public OrderStatus getOrderStatus() { return orderStatus; }
    public void setOrderStatus(OrderStatus orderStatus) { this.orderStatus = orderStatus; }

    public DeliveryOtp getDeliveryOtp() { return deliveryOtp; }
    public void setDeliveryOtp(DeliveryOtp deliveryOtp) { this.deliveryOtp = deliveryOtp; }

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

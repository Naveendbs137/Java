package com.dhatvibs.controller;

import com.dhatvibs.entity.Order;
import com.dhatvibs.entity.OrderStatus;
import com.dhatvibs.repository.OrderRepository;
import com.dhatvibs.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public OrdersController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    // 1️⃣ Incoming orders
    @GetMapping("/incoming")
    public List<Order> incoming(@RequestParam Long riderId) {
        return orderRepository.findByRiderIdAndOrderStatus(riderId, OrderStatus.ASSIGNED);
    }

    // 2️⃣ Accept
    @PostMapping("/{id}/accept")
    public Order accept(@PathVariable Long id) {
        return orderService.updateStatus(id, OrderStatus.CONFIRMED);
    }

    // 3️⃣ Reject
    @PostMapping("/{id}/reject")
    public Order reject(@PathVariable Long id) {
        return orderService.updateStatus(id, OrderStatus.CANCELLED);
    }

    // 4️⃣ Details
    @GetMapping("/{id}")
    public Order details(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // 5️⃣ Arrived pickup
    @PostMapping("/{id}/arrived-pickup")
    public Order arrivedPickup(@PathVariable Long id) {
        return orderService.updateStatus(id, OrderStatus.CONFIRMED);
    }

    // Pickup
    @PostMapping("/{id}/pickup")
    public Order pickup(@PathVariable Long id) {
        return orderService.updateStatus(id, OrderStatus.PICKED_UP);
    }

    // Arrived drop
    @PostMapping("/{id}/arrived-drop")
    public Order arrivedDrop(@PathVariable Long id) {
        return orderService.updateStatus(id, OrderStatus.PICKED_UP);
    }

    // Deliver
    @PostMapping("/{id}/deliver")
    public Order deliver(@PathVariable Long id) {
        return orderService.updateStatus(id, OrderStatus.DELIVERED);
    }

    // OTP verify
    @PostMapping("/{id}/deliver/otp")
    public Order verifyOtp(@PathVariable Long id,
                           @RequestParam String otp) {
        return orderService.verifyOtp(id, otp);
    }

    // Delivery proof
    @PostMapping("/{id}/deliver/proof")
    public String proof(@PathVariable Long id) {
        return "Proof uploaded";
    }

    // Issue
    @PostMapping("/{id}/issue")
    public String issue(@PathVariable Long id) {
        return "Issue reported";
    }

    // History
    @GetMapping("/history")
    public List<Order> history(@RequestParam Long riderId) {
        return orderRepository.findByRiderId(riderId);
    }
    
    //for Testing purpose-random order created
    @PostMapping("/create")
    public Order create(@RequestBody Order order) {
        return orderRepository.save(order);
    }
    
    @GetMapping("/active")
    public Order getActiveOrder(@RequestParam Long riderId) {
        return orderService.getActiveOrder(riderId);
    }


}


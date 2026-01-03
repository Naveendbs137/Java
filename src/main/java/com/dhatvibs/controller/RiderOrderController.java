package com.dhatvibs.controller;

import com.dhatvibs.entity.Order;
import com.dhatvibs.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rider")
@CrossOrigin
public class RiderOrderController {

    private final OrderService orderService;

    public RiderOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/active-order")
    public Map<String, String> getActiveOrder(@RequestParam Long riderId) {
        Order order = orderService.getActiveOrder(riderId);
        return Map.of("orderId", order.getOrderId());
    }
}

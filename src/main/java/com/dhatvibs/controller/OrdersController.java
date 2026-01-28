/*
 * package com.dhatvibs.controller;
 * 
 * import com.dhatvibs.entity.Order; import com.dhatvibs.entity.OrderStatus;
 * import com.dhatvibs.repository.OrderRepository; import
 * com.dhatvibs.service.OrderService; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import java.util.List;
 * 
 * @RestController
 * 
 * @RequestMapping("/orders") public class OrdersController {
 * 
 * private final OrderRepository orderRepository; private final OrderService
 * orderService;
 * 
 * public OrdersController(OrderRepository orderRepository, OrderService
 * orderService) { this.orderRepository = orderRepository; this.orderService =
 * orderService; }
 * 
 * // 1️⃣ Incoming orders
 * 
 * @GetMapping("/incoming") public List<Order> incoming(@RequestParam Long
 * riderId) { return orderRepository.findByRiderIdAndOrderStatus(riderId,
 * OrderStatus.ASSIGNED); }
 * 
 * // 2️⃣ Accept
 * 
 * @PostMapping("/{id}/accept") public Order accept(@PathVariable Long id) {
 * return orderService.updateStatus(id, OrderStatus.CONFIRMED); }
 * 
 * // 3️⃣ Reject
 * 
 * @PostMapping("/{id}/reject") public Order reject(@PathVariable Long id) {
 * return orderService.updateStatus(id, OrderStatus.CANCELLED); }
 * 
 * // 4️⃣ Details
 * 
 * @GetMapping("/{id}") public Order details(@PathVariable Long id) { return
 * orderRepository.findById(id) .orElseThrow(() -> new
 * RuntimeException("Order not found")); }
 * 
 * // 5️⃣ Arrived pickup
 * 
 * @PostMapping("/{id}/arrived-pickup") public Order arrivedPickup(@PathVariable
 * Long id) { return orderService.updateStatus(id, OrderStatus.CONFIRMED); }
 * 
 * // Pickup
 * 
 * @PostMapping("/{id}/pickup") public Order pickup(@PathVariable Long id) {
 * return orderService.updateStatus(id, OrderStatus.PICKED_UP); }
 * 
 * // Arrived drop
 * 
 * @PostMapping("/{id}/arrived-drop") public Order arrivedDrop(@PathVariable
 * Long id) { return orderService.updateStatus(id, OrderStatus.PICKED_UP); }
 * 
 * // Deliver
 * 
 * @PostMapping("/{id}/deliver") public Order deliver(@PathVariable Long id) {
 * return orderService.updateStatus(id, OrderStatus.DELIVERED); }
 * 
 * // OTP verify
 * 
 * @PostMapping("/{id}/deliver/otp") public Order verifyOtp(@PathVariable Long
 * id,
 * 
 * @RequestParam String otp) { return orderService.verifyOtp(id, otp); }
 * 
 * // Delivery proof
 * 
 * @PostMapping("/{id}/deliver/proof") public String proof(@PathVariable Long
 * id) { return "Proof uploaded"; }
 * 
 * // Issue
 * 
 * @PostMapping("/{id}/issue") public String issue(@PathVariable Long id) {
 * return "Issue reported"; }
 * 
 * // History
 * 
 * @GetMapping("/history") public List<Order> history(@RequestParam Long
 * riderId) { return orderRepository.findByRiderId(riderId); }
 * 
 * //for Testing purpose-random order created
 * 
 * @PostMapping("/create") public Order create(@RequestBody Order order) {
 * return orderRepository.save(order); }
 * 
 * @GetMapping("/active") public Order getActiveOrder(@RequestParam Long
 * riderId) { return orderService.getActiveOrder(riderId); }
 * 
 * 
 * }
 * 
 */ 

package com.dhatvibs.controller;

import com.dhatvibs.entity.Order;
import com.dhatvibs.entity.OrderStatus;
import com.dhatvibs.repository.OrderRepository;
import com.dhatvibs.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public OrdersController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    // 1️⃣ Incoming orders
    @GetMapping("/incoming")
    public Map<String, Object> incoming(@RequestParam Long riderId) {

        List<Order> orders =
                orderRepository.findByRiderIdAndOrderStatus(riderId, OrderStatus.ASSIGNED);

        return Map.of(
                "success", true,
                "message", "Incoming orders fetched successfully",
                "data", orders
        );
    }

    // 2️⃣ Accept order
    @PostMapping("/{id}/accept")
    public Map<String, Object> accept(@PathVariable Long id) {

        Order order = orderService.updateStatus(id, OrderStatus.CONFIRMED);

        return Map.of(
                "success", true,
                "message", "Order accepted successfully",
                "data", order
        );
    }

    // 3️⃣ Reject order
    @PostMapping("/{id}/reject")
    public Map<String, Object> reject(@PathVariable Long id) {

        Order order = orderService.updateStatus(id, OrderStatus.CANCELLED);

        return Map.of(
                "success", true,
                "message", "Order rejected successfully",
                "data", order
        );
    }

    // 4️⃣ Order details
    @GetMapping("/{id}")
    public Map<String, Object> details(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return Map.of(
                "success", true,
                "message", "Order details fetched successfully",
                "data", order
        );
    }

    // 5️⃣ Arrived at pickup
    @PostMapping("/{id}/arrived-pickup")
    public Map<String, Object> arrivedPickup(@PathVariable Long id) {

        Order order = orderService.updateStatus(id, OrderStatus.CONFIRMED);

        return Map.of(
                "success", true,
                "message", "Arrived at pickup location",
                "data", order
        );
    }

    // Pickup
    @PostMapping("/{id}/pickup")
    public Map<String, Object> pickup(@PathVariable Long id) {

        Order order = orderService.updateStatus(id, OrderStatus.PICKED_UP);

        return Map.of(
                "success", true,
                "message", "Order picked up successfully",
                "data", order
        );
    }

    // Arrived at drop
    @PostMapping("/{id}/arrived-drop")
    public Map<String, Object> arrivedDrop(@PathVariable Long id) {

        Order order = orderService.updateStatus(id, OrderStatus.PICKED_UP);

        return Map.of(
                "success", true,
                "message", "Arrived at drop location",
                "data", order
        );
    }

    // Deliver
    @PostMapping("/{id}/deliver")
    public Map<String, Object> deliver(@PathVariable Long id) {

        Order order = orderService.updateStatus(id, OrderStatus.DELIVERED);

        return Map.of(
                "success", true,
                "message", "Order delivered successfully",
                "data", order
        );
    }

    // OTP verification
    @PostMapping("/{id}/deliver/otp")
    public Map<String, Object> verifyOtp(
            @PathVariable Long id,
            @RequestParam String otp) {

        Order order = orderService.verifyOtp(id, otp);

        return Map.of(
                "success", true,
                "message", "OTP verified successfully",
                "data", order
        );
    }

    // Delivery proof
    @PostMapping("/{id}/deliver/proof")
    public Map<String, Object> proof(@PathVariable Long id) {

        return Map.of(
                "success", true,
                "message", "Delivery proof uploaded successfully"
        );
    }

    // Report issue
    @PostMapping("/{id}/issue")
    public Map<String, Object> issue(@PathVariable Long id) {

        return Map.of(
                "success", true,
                "message", "Issue reported successfully"
        );
    }

    // Order history
    @GetMapping("/history")
    public Map<String, Object> history(@RequestParam Long riderId) {

        List<Order> orders = orderRepository.findByRiderId(riderId);

        return Map.of(
                "success", true,
                "message", "Order history fetched successfully",
                "data", orders
        );
    }

    // For testing – create random order
    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody Order order) {

        Order saved = orderRepository.save(order);

        return Map.of(
                "success", true,
                "message", "Order created successfully",
                "data", saved
        );
    }

    // Active order
    @GetMapping("/active")
    public Map<String, Object> getActiveOrder(@RequestParam Long riderId) {

        Order order = orderService.getActiveOrder(riderId);

        return Map.of(
                "success", true,
                "message", "Active order fetched successfully",
                "data", order
        );
    }
}

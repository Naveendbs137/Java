package com.dhatvibs.serviceImpl;

import com.dhatvibs.entity.Order;
import com.dhatvibs.entity.OrderStatus;
import com.dhatvibs.repository.OrderRepository;
import com.dhatvibs.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getActiveOrder(Long riderId) {
        return orderRepository
                .findFirstByRiderIdAndOrderStatusIn(
                        riderId,
                        List.of(
                                OrderStatus.ASSIGNED,
                                OrderStatus.CONFIRMED,
                                OrderStatus.PICKED_UP
                        )
                )
                .orElseThrow(() ->
                        new RuntimeException("No active order")
                );
    }


    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order updateStatus(Long id, OrderStatus status) {
        Order order = getById(id);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getHistory(Long riderId) {
        return orderRepository.findByRiderId(riderId);
    }

    @Override
    public Order verifyOtp(Long id, String otp) {
        Order order = getById(id);

        if (order.getDeliveryOtp() == null ||
                !otp.equals(order.getDeliveryOtp().getCode())) {
            throw new RuntimeException("Invalid OTP");
        }

        order.getDeliveryOtp().setVerified(true);
        order.setOrderStatus(OrderStatus.DELIVERED);
        return orderRepository.save(order);
    }
}

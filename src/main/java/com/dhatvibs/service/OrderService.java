package com.dhatvibs.service;

import com.dhatvibs.entity.Order;
import com.dhatvibs.entity.OrderStatus;

import java.util.List;

public interface OrderService {

    Order getActiveOrder(Long riderId);

    Order updateStatus(Long orderDbId, OrderStatus status);

    Order verifyOtp(Long orderDbId, String otp);

    List<Order> getHistory(Long riderId);

    Order getById(Long id);
}

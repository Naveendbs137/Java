package com.dhatvibs.repository;

import com.dhatvibs.entity.Order;
import com.dhatvibs.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findFirstByRiderIdAndOrderStatusIn(
	        Long riderId,
	        List<OrderStatus> statuses
	);

    List<Order> findByRiderId(Long riderId);

    List<Order> findByRiderIdAndOrderStatus(Long riderId, OrderStatus status);
}

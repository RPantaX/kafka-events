package com.appsdeveloperblog.orders.service;

import com.appsdeveloperblog.core.dto.Order;

import java.util.UUID;

public interface OrderService {
    void rejectOrder(UUID orderId);
    void  approveOrder(UUID orderId);
    Order placeOrder(Order order);
}

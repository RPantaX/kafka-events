package com.appsdeveloperblog.orders.service;

import com.appsdeveloperblog.core.dto.Order;
import com.appsdeveloperblog.core.dto.events.OrderApprovedEvent;
import com.appsdeveloperblog.core.dto.events.OrderCreatedEvent;
import com.appsdeveloperblog.core.types.OrderStatus;
import com.appsdeveloperblog.orders.dao.jpa.entity.OrderEntity;
import com.appsdeveloperblog.orders.dao.jpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String ordersEentsTopicName;
    public OrderServiceImpl(OrderRepository orderRepository,
                            KafkaTemplate<String, Object> kafkaTemplate,
                            @Value("${orders.events.topic.name}") String ordersEentsTopicName) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.ordersEentsTopicName = ordersEentsTopicName;
    }

    @Override
    public void rejectOrder(UUID orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        Assert.notNull(orderEntity, "Order not found");
        orderEntity.setStatus(OrderStatus.REJECTED);
        orderRepository.save(orderEntity);
    }

    @Override
    public void approveOrder(UUID orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        Assert.notNull(orderEntity, "No order is found with id "+orderId+" in the database table");
        orderEntity.setStatus(OrderStatus.APPROVED);
        orderRepository.save(orderEntity);
        OrderApprovedEvent orderApprovedEvent = new OrderApprovedEvent(orderId);
        kafkaTemplate.send(ordersEentsTopicName, orderApprovedEvent);
    }

    @Override
    public Order placeOrder(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setCustomerId(order.getCustomerId());
        entity.setProductId(order.getProductId());
        entity.setProductQuantity(order.getProductQuantity());
        entity.setStatus(OrderStatus.CREATED);
        orderRepository.save(entity);

        OrderCreatedEvent placeOrder = new OrderCreatedEvent(
                entity.getId(),
                order.getProductQuantity(),
                order.getProductId(),
                entity.getCustomerId()
        );

        kafkaTemplate.send(ordersEentsTopicName, placeOrder);


        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getProductId(),
                entity.getProductQuantity(),
                entity.getStatus());
    }

}

package com.appsdeveloperblog.core.dto.events;

import java.util.UUID;

public class OrderCreatedEvent {
    private UUID orderId;
    private UUID customerId;
    private UUID productId;
    private Integer productQuantity;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(UUID orderId, Integer productQuantity, UUID productId, UUID customerId) {
        this.orderId = orderId;
        this.productQuantity = productQuantity;
        this.productId = productId;
        this.customerId = customerId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}

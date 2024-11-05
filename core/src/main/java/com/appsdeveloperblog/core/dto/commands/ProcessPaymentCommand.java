package com.appsdeveloperblog.core.dto.commands;

import java.math.BigDecimal;
import java.util.UUID;

public class ProcessPaymentCommand {
    private UUID orderdId;
    private UUID productId;
    private BigDecimal productPrice;
    private Integer productQuantity;

    public ProcessPaymentCommand() {
    }

    public ProcessPaymentCommand(UUID orderdId, UUID productId, BigDecimal productPrice, Integer productQuantity) {
        this.orderdId = orderdId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public UUID getOrderdId() {
        return orderdId;
    }

    public void setOrderdId(UUID orderdId) {
        this.orderdId = orderdId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}

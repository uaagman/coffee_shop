package com.ashokn.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by ashok on 6/21/17.
 */
public class OrderLines{
    @NotNull
    @Min(1)
    private int quantity;
    @NotNull
    private int productId;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderLines{" +
                "quantity=" + quantity +
                ", productId=" + productId +
                '}';
    }
}

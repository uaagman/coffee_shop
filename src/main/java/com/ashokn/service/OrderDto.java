package com.ashokn.service;

import com.ashokn.model.Orderline;

import javax.annotation.Resource;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ashok on 6/21/17.
 */
public class OrderDto {

    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @NotNull
    private int personId;
    @Valid
    private List<OrderLines> orderLines;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public List<OrderLines> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLines> orderLine) {
        this.orderLines = orderLine;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                ", orderDate=" + orderDate +
                ", personId=" + personId +
                ", orderLine=" + orderLines +
                '}';
    }
}

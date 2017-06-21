package com.ashokn.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order extends Model{

	@NotNull(message = "Order date can't be empty")
	@Temporal(TemporalType.DATE)
	private Date orderDate;

	@NotNull(message = "OrderLines shouldn't be null")
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Valid
	@JsonManagedReference
	private List<Orderline> orderLines = new ArrayList<>();
    @NotNull(message = "Person shouldn't be null")
	@OneToOne()
	private Person person;

	public int getId() {
		return id;
	}

	public List<Orderline> getOrderLines() {
		return orderLines;
	}

    public void setOrderLines(List<Orderline> orderLines) {
        this.orderLines = orderLines;
    }

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		int quantity = 0;
		for (Orderline ol : this.orderLines) {
			quantity += ol.getQuantity();
		}
		return quantity;
	}

	public double getTotalAmount() {
		double totalAmount = 0;

		for (Orderline ol : this.orderLines) {
			totalAmount += ol.getSubtotal();
		}
		return totalAmount;
	}

	public void addOrderLine(Orderline orderLine) {
		orderLine.setOrder(this);
		this.orderLines.add(orderLine);
	}

	public void removeOrderLine(Orderline orderLine) {
		this.orderLines.remove(orderLine);
		orderLine.setOrder(null);
	}

	public void clearOrderLines() {
		for (Orderline orderline : orderLines) {
			orderline.setOrder(null);
		}
		orderLines.clear();
	}

    @Override
    public String toString() {
        return "Order{" +
                "orderDate=" + orderDate +
                ", orderLines=" + orderLines +
                ", person=" + person +
                '}';
    }
}

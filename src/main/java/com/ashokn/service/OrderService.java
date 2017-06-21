package com.ashokn.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ashokn.model.Order;
import com.ashokn.model.Orderline;
import com.ashokn.model.Person;
import com.ashokn.model.Product;
import com.ashokn.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class OrderService {
	private final OrderRepository orderRepository;
	@Resource
	private PersonService personService;
	@Resource
    ProductService productService;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order save(OrderDto order){
		Order order1 = new Order();
		order1.setOrderLines(new ArrayList<>());
		order1.setOrderDate(order.getOrderDate());
		order1.setPerson(personService.findById(order.getPersonId()));

        List<OrderLines> orderLines = order.getOrderLines();
        for(OrderLines orderLine : orderLines){
            Orderline ord = new Orderline();
            ord.setQuantity(orderLine.getQuantity());
            ord.setProduct(productService.getProduct(orderLine.getProductId()));

            order1.addOrderLine(ord);
        }
        return orderRepository.save(order1);
	}
	
	public void delete(Order order){
		orderRepository.delete(order);
	}
	
	public List<Order> findByProduct(Product product) {
		return orderRepository.findDistinctOrderByOrderLines_Product(product);
	}
	
	public List<Order> findByPerson(Person person) {
		return orderRepository.findOrderByPerson(person);
	}

	public List<Order> findByDate(Date minDate, Date maxDate) {
		return orderRepository.findOrderByOrderDateBetween(minDate, maxDate);
	}

	public Order findById(int id){
		return orderRepository.findOne(id);
	}

	public List<Order> findAll(){
		return orderRepository.findAll();
	}

}

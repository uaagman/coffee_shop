package com.ashokn.repository;

import com.ashokn.model.Order;
import com.ashokn.model.Person;
import com.ashokn.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> findDistinctOrderByOrderLines_Product(Product product);
	List<Order> findOrderByPerson(Person person);
	List<Order> findOrderByOrderDateBetween(Date minDate, Date maxDate);
	

}

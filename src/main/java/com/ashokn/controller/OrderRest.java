package com.ashokn.controller;

import com.ashokn.model.Order;
import com.ashokn.model.Person;
import com.ashokn.model.Product;
import com.ashokn.service.OrderDto;
import com.ashokn.service.OrderService;
import com.ashokn.service.PersonService;
import com.ashokn.service.ProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

/**
 * Created by ashok on 6/18/17.
 */
@RestController
@RequestMapping("/rs/order")
public class OrderRest {

    @Resource
    private OrderService orderService;
    @Resource
    private ProductService productService;
    @Resource
    private PersonService personService;

    @GetMapping("")
    public Response allOrders(){
        return Response.ok().entity(orderService.findAll()).build();
    }

    @GetMapping("/product")
    public Response findByProduct(@RequestParam("pId") int proId){
        Product product = productService.getProduct(proId);
        return Response.ok().entity(orderService.findByProduct(product)).build();
    }

    @GetMapping("/person")
    public Response findByPerson(@RequestParam("pId") int proId){
        Person person = personService.findById(proId);
        return Response.ok().entity(orderService.findByPerson(person)).build();
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable int id){
        return Response.ok().entity(orderService.findById(id)).build();
    }

    @PostMapping("")
    public Response saveOrder(@Validated @RequestBody OrderDto order){
        try {
            Order ord = orderService.save(order);
            return Response.ok().entity(ord).build();
        }catch (Exception ex){
//            return Response.status(500).entity("Can't save order").build();
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }

}

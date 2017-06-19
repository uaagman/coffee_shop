package com.ashokn.controller;

import com.ashokn.model.Product;
import com.ashokn.service.ProductService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;

/**
 * Created by ashok on 6/17/17.
 */
@RestController
@RequestMapping("/rs/product")
public class ProductRest {
    @Resource
    private ProductService productService;

    @GetMapping("")
    public Response get(){
        return Response.ok().entity(productService.getAllProduct()).build();
    }

    @GetMapping("/{id}")
    public Response getOne(@PathVariable("id") int id){
        return Response.ok().entity(productService.getProduct(id)).build();
    }

    @GetMapping(value = "/search")
    public Response getSearched(@RequestParam("q") @NotNull String search){
        return Response.ok().entity(productService.findByTextSearch(search)).build();
    }

    @GetMapping(value = "/price")
    public Response getPriceBetween(@RequestParam("min") @NotNull double min,@RequestParam("max") @NotNull double max){
        return Response.ok().entity(productService.findByPrice(min,max)).build();
    }

    @PostMapping(value = "")
    public Response create(@Validated @RequestBody Product product){
        try {
            Product pro = productService.save(product);
            return Response.ok().entity(pro).build();
        }catch (Exception ex){
            return Response.status(500).entity("Can't create product. Check input and try again").build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public Response remove(@PathVariable("id") int id){
        try{
            productService.delete(id);
            return Response.ok().entity("Deleted product : "+id).build();
        }catch (Exception ex){
            return Response.status(500).entity("Error deleting product!").build();
        }
    }

    @PutMapping(value = "/{id}")
    public Response update(@PathVariable("id") int id,@Validated @RequestBody Product product){
        try{
            product.setId(id);
            productService.save(product);
            return Response.ok().entity(productService.getProduct(id)).build();
        }catch (Exception ex){
            return Response.status(500).entity("Error updating product!").build();
        }
    }
}

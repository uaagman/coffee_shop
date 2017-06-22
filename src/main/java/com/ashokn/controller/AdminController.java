package com.ashokn.controller;

import com.ashokn.model.Authority;
import com.ashokn.model.Person;
import com.ashokn.model.Product;
import com.ashokn.model.ProductType;
import com.ashokn.service.OrderService;
import com.ashokn.service.PersonService;
import com.ashokn.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;

/**
 * Created by ashok on 6/21/17.
 */
@Controller
public class AdminController {
    @Resource
    private ProductService productService;
    @Resource
    private PersonService personService;
    @Resource
    private OrderService orderService;

    /* Admin */
    @GetMapping("/products")
    public String productLists(Model model){
        model.addAttribute("products",productService.getAllProduct());
        return "products";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(Model model, @PathVariable int id){
        model.addAttribute("product",productService.getProduct(id));
        model.addAttribute("productTypes", ProductType.values());
        return "editProduct";
    }

    @PostMapping("/editProduct/{id}")
    public String editProductPost(@Valid @ModelAttribute("pro")Product product, BindingResult result, @PathVariable int id, RedirectAttributes attributes){
        if(!result.hasErrors()) {
            product.setId(id);
            Product product1 = productService.getProduct(id);
            product.setUpdatedAt(new Date());
            product.setCreatedAt(product1.getCreatedAt());
            productService.save(product);
            attributes.addFlashAttribute("success","Successful edited product");
            return "redirect:/editProduct/"+id;
        }else{
            attributes.addFlashAttribute("success","Edit Failed");
            return null;
        }
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model){
        model.addAttribute("productTypes", ProductType.values());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProductPost(@Valid @ModelAttribute("pro") Product product, BindingResult result, RedirectAttributes redirectAttributes){
        if(!result.hasErrors()){
            productService.save(product);
            redirectAttributes.addFlashAttribute("seccess","Added Product : "+product.getProductName());
            return "redirect:/products";
        }else {
            redirectAttributes.addFlashAttribute("seccess","Added Product : "+product.getProductName());
            return null;
        }
    }

    @GetMapping("/allOrders")
    public String allOrders(Model model){
        model.addAttribute("orders",orderService.findAll());
        return "allOrders";
    }

    @GetMapping("/persons")
    public String allPersons(Model model){
        model.addAttribute("users",personService.findAll());
        return "persons";
    }

    @GetMapping("/addUser")
    public String addPerson(){
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addPersonPost(@Valid @ModelAttribute("usr") Person person, BindingResult result, RedirectAttributes redirectAttributes, String authority){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("succes","Error Adding User");
            return null;
        }else{
            person.setEnabled(true);
            Authority authority1 = new Authority(person,authority);
            person.setAuthorities(Collections.singletonList(authority1));
            personService.savePersonAdmin(person);
            redirectAttributes.addFlashAttribute("succes","User --"+person.getEmail()+"-- Added");
            return "redirect:/persons";
        }
    }
}

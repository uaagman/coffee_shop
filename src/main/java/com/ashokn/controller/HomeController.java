package com.ashokn.controller;

import com.ashokn.model.Authority;
import com.ashokn.model.Person;
import com.ashokn.model.Product;
import com.ashokn.model.ProductType;
import com.ashokn.service.*;
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
import javax.ws.rs.GET;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@Controller
public class HomeController {
    @Resource
    private ProductService productService;
    @Resource
    private PersonService personService;
    @Resource
    private OrderService orderService;
	
	@GetMapping({"/", "/index", "/home"})
	public String homePage(Model model) {
		model.addAttribute("products",productService.getAllProduct());
		return "home";
	}

	@GetMapping("/login")
	public String loginPage(Principal principal){
        return (principal == null)?"login":"redirect:/home";
	}

    @GetMapping("/register")
    public String register(Principal principal){
		return (principal == null)?"register":"redirect:/home";
    }

    @PostMapping("/register")
	public String register(@Valid Person person, BindingResult result, Model model, Principal principal){
        if(principal != null){
            return "redirect:/home";
        }
        if(!result.hasErrors()){
            personService.savePerson(person);
            return "redirect:/login";
        }else{
            return null;
        }

	}

	@PostMapping("/order")
    public String orderNow(@Valid OrderLines orderLines,BindingResult result, Principal principal, RedirectAttributes redirectAttributes){
	    Person person = personService.findByEmail(principal.getName());
	    if(person != null){
            OrderDto orderDto = new OrderDto();
            orderDto.setPersonId(person.getId());
            orderDto.setOrderDate(new Date());
            orderDto.setOrderLines(Collections.singletonList(orderLines));
            orderService.save(orderDto);
            redirectAttributes.addFlashAttribute("success","Your Order has been saved");
            return "redirect:/order";
        }
        redirectAttributes.addFlashAttribute("error","Error Saving order. Please try again later.");
        return "redirect:/home";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal){
        model.addAttribute("profile",personService.findByEmail(principal.getName()));
        return "profile";
    }

    @PostMapping("/updateProfile/{id}")
    public String updateProfile(@Valid Person person,BindingResult result,@PathVariable int id,RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("success","Error Updating");
        }else {
            person.setId(id);
            personService.updatePerson(person);
            redirectAttributes.addFlashAttribute("success", "Updated profile successfully");
        }
        return "redirect:/profile";
    }

    @GetMapping("/order")
    public String viewOrders(Model model, Principal principal){
        Person person = personService.findByEmail(principal.getName());
        model.addAttribute("orders",orderService.findByPerson(person));
        return "orders";
    }

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
}

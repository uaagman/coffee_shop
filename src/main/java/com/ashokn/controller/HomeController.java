package com.ashokn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
	
	@GetMapping({"/", "/index", "/home"})
	public String homePage() {
		return "home";
	}

	@GetMapping("/login")
	public String loginPage(Principal principal){
        return (principal == null)?"login":"redirect:/secure";
	}

    @GetMapping("/register")
    public String register(){
        return "register";
    }

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
}

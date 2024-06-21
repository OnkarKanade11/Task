package com.ats_prototype.atsprototype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//	 controller for handling the login and default home page
	
	@GetMapping("/index")
    public String home() {
        return "index"; // Return the name of your home page template
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Return the name of your login page template
    }

}

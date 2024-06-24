package com.ats_prototype.atsprototype.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String home() {
        return "index"; // Return the name of your home page template
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Return the name of your login page template
    }

    @GetMapping("/login-success")
    public String loginSuccess(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        switch (role) {
            case "ROLE_CANDIDATE":
                return "redirect:/candidate/dashboard";
            case "ROLE_EMPLOYER":
                return "redirect:/employer/dashboard";
            case "ROLE_RECRUITER":
                return "redirect:/recruiter/dashboard";
            case "ROLE_COORDINATOR":
                return "redirect:/coordinator/dashboard";
            default:
                return "redirect:/";
        }
    }
}


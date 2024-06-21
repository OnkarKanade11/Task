package com.ats_prototype.atsprototype.controller;

 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ats_prototype.atsprototype.service.RecruiterService;

@Controller
@RequestMapping("/recruiter")
public class RecruiterController {

 private final RecruiterService recruiterService;

 public RecruiterController(RecruiterService recruiterService) {
     this.recruiterService = recruiterService;
 }

 @GetMapping("/dashboard")
 public String showRecruiterDashboard(Model model) {
     // Implement logic to fetch data for the recruiter dashboard
     return "recruiter-dashboard";
 }

 @GetMapping("/review")
 public String showApplicationReviewForm(Model model) {
     // Implement logic to fetch candidate application and R2 check form
     return "recruiter-applicationReview";
 }

 @PostMapping("/review")
 public String submitReview() {
     // Implement logic to save R2 check form responses and shortlist candidate
     return "redirect:/recruiter-dashboard";
 }
}

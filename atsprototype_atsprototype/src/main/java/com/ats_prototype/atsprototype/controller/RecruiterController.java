package com.ats_prototype.atsprototype.controller;

 
import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ats_prototype.atsprototype.entity.JobApplication;
import com.ats_prototype.atsprototype.service.JobApplicationService;
import com.ats_prototype.atsprototype.service.RecruiterService;

@Controller
@RequestMapping("/recruiter")
public class RecruiterController {

    private final RecruiterService recruiterService;
    private final JobApplicationService jobApplicationService;

    @Autowired
    public RecruiterController(RecruiterService recruiterService,JobApplicationService jobApplicationService) {
        this.recruiterService = recruiterService;
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        JobApplication nextApplication = jobApplicationService.getNextApplicationForReview();
        model.addAttribute("application", nextApplication);
        return "recruiter/dashboard";
    }

    @GetMapping("/review/{applicationId}")
     public String showReviewForm(@PathVariable Long applicationId, Model model) {
         JobApplication application = jobApplicationService.getApplicationById(applicationId);
         model.addAttribute("application", application);
         return "recruiter/review-form";
      }

        @PostMapping("/review")
        public String submitReviewAndShortlist(@RequestParam Long applicationId, 
                                               @RequestParam Map<String, String> r2Responses,
                                               @RequestParam(required = false) boolean shortlist) {
            jobApplicationService.submitR2CheckAndShortlist(applicationId, r2Responses, shortlist);
            return "redirect:/recruiter/dashboard";
        }
    }
}

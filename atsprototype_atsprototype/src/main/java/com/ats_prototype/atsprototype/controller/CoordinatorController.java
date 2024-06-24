package com.ats_prototype.atsprototype.controller;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ats_prototype.atsprototype.entity.JobPosting;
import com.ats_prototype.atsprototype.entity.Recruiter;
import com.ats_prototype.atsprototype.service.JobPostingService;
import com.ats_prototype.atsprototype.service.RecruiterService;

@Controller
@RequestMapping("/coordinator")
public class CoordinatorController {

    private final JobPostingService jobPostingService;
    @Autowired
    private RecruiterService recruiterService;


    @Autowired
    public CoordinatorController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @GetMapping("/dashboard")
    public String showCoordinatorDashboard(Model model) {
        List<JobPosting> jobPostings = jobPostingService.getAllJobPostings();
        model.addAttribute("jobPostings", jobPostings);
        return "coordinator-dashboard";
    }

    @GetMapping("/job-posting/{id}")
    public String viewJobPosting(@PathVariable Long id, Model model) {
        JobPosting jobPosting = jobPostingService.getJobPostingById(id);
        List<Recruiter> recruiters = recruiterService.getAllRecruiters();
        model.addAttribute("jobPosting", jobPosting);
        model.addAttribute("recruiters", recruiters);
        return "coordinator/job-posting-details";
    }

    @PostMapping("/job-posting/{id}")
    public String approveJobPosting(@PathVariable Long id, @ModelAttribute("jobPosting") JobPosting jobPosting) {
        // Implement logic to update job posting details
        jobPostingService.updateJobPosting(jobPosting);
        return "redirect:/coordinator/dashboard";
    }
    
    @PostMapping("/assign-recruiter")
    public String assignRecruiter(@RequestParam Long jobPostingId, @RequestParam Long recruiterId) {
        jobPostingService.assignRecruiterToJobPosting(jobPostingId, recruiterId);
        return "redirect:/coordinator/job-posting/" + jobPostingId;
    }

    @PostMapping("/add-r2-check")
    public String addR2Check(@RequestParam Long jobPostingId, @RequestParam List<String> r2Questions) {
        jobPostingService.addR2CheckQuestions(jobPostingId, r2Questions);
        return "redirect:/coordinator/job-posting/" + jobPostingId;
    }
     
}
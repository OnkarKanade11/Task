package com.ats_prototype.atsprototype.controller;

 
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ats_prototype.atsprototype.entity.JobPosting;
import com.ats_prototype.atsprototype.entity.Question;
import com.ats_prototype.atsprototype.service.JobPostingService;

@Controller
@RequestMapping("/coordinator")
public class CoordinatorController {

    private final JobPostingService jobPostingService;

    public CoordinatorController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @GetMapping("/dashboard")
    public String showCoordinatorDashboard(Model model) {
        // Fetch all job postings from the database
        List<JobPosting> jobPostings = jobPostingService.getAllJobPostings();
        model.addAttribute("jobPostings", jobPostings);
        return "coordinator-dashboard";
    }

    @GetMapping("/job-posting/{id}")
    public String showJobPostingDetails(@PathVariable Long id, Model model) {
        // Fetch the job posting from the database
        JobPosting jobPosting = jobPostingService.getJobPostingById(id);
        model.addAttribute("jobPosting", jobPosting);
        // Add an empty list of questions for the R2 check
        List<Question> r2CheckQuestions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            r2CheckQuestions.add(new Question(id, null, null));
        }
        model.addAttribute("r2CheckQuestions", r2CheckQuestions);
        return "edit-job-posting";
    }

    @PostMapping("/job-posting/{id}")
    public String approveJobPosting(@PathVariable Long id, @ModelAttribute("jobPosting") JobPosting jobPosting) {
        // Update the job posting with the R2 check questions
        JobPosting existingJobPosting = jobPostingService.getJobPostingById(id);
        existingJobPosting.setR2CheckQuestions(jobPosting.getR2CheckQuestions());
        jobPostingService.updateJobPosting(existingJobPosting);
        // Redirect to the coordinator dashboard or a success page
        return "redirect:/coordinator/dashboard";
    }
}

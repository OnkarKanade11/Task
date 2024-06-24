package com.ats_prototype.atsprototype.controller;

 
import java.security.Principal;
import java.util.ArrayList;
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

import com.ats_prototype.atsprototype.entity.Candidate;
import com.ats_prototype.atsprototype.entity.Employer;
import com.ats_prototype.atsprototype.entity.JobPosting;
import com.ats_prototype.atsprototype.entity.Question;
import com.ats_prototype.atsprototype.service.CandidateService;
import com.ats_prototype.atsprototype.service.EmployerService;
import com.ats_prototype.atsprototype.service.JobPostingService;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    private final EmployerService employerService;
    private final JobPostingService jobPostingService;

    @Autowired
    public EmployerController(EmployerService employerService, JobPostingService jobPostingService) {
        this.employerService = employerService;
        this.jobPostingService = jobPostingService;
    }

    @GetMapping("/dashboard")
    public String showEmployerDashboard(Model model, Principal principal) {
        String employerEmail = principal.getName();
        Employer employer = employerService.getEmployerByEmail(employerEmail);
        model.addAttribute("employer", employer);

        List<JobPosting> jobPostings = jobPostingService.getJobPostingsByEmployer(employer);
        model.addAttribute("jobPostings", jobPostings);

        return "employer-dashboard";
    }

    @GetMapping("/job-posting")
    public String showJobPostingForm(Model model) {
        JobPosting jobPosting = new JobPosting();
        model.addAttribute("jobPosting", jobPosting);
        return "create-job-posting";
    }

    @PostMapping("/job-posting")
    public String createJobPosting(@ModelAttribute JobPosting jobPosting,
                                   @RequestParam(value = "r1Questions", required = false) List<String> r1Questions,
                                   @RequestParam(value = "r1Answers", required = false) List<Boolean> r1Answers,
                                   Principal principal) {
        String employerEmail = principal.getName();
        Employer employer = employerService.getEmployerByEmail(employerEmail);
        jobPosting.setEmployer(employer);

        // Add R1 check questions
        if (r1Questions != null && r1Answers != null && r1Questions.size() == r1Answers.size()) {
            for (int i = 0; i < r1Questions.size(); i++) {
                Question question = new Question();
                question.setQuestion(r1Questions.get(i));
                question.setCorrectAnswer(r1Answers.get(i));
                jobPosting.addR1CheckQuestion(question);
            }
        }

        jobPostingService.createJobPosting(jobPosting);
        
        return "redirect:/employer/dashboard";
    }
    
    @GetMapping("/job-posting/{id}")
    public String viewJobPostingDetails(@PathVariable Long id, Model model) {
        JobPosting jobPosting = jobPostingService.getJobPostingById(id);
        model.addAttribute("jobPosting", jobPosting);
        return "job-posting-details";
    }
    
    @GetMapping("/job-posting/{id}/edit")
    public String showEditJobPostingForm(@PathVariable Long id, Model model) {
        JobPosting jobPosting = jobPostingService.getJobPostingById(id);
        model.addAttribute("jobPosting", jobPosting);
        return "edit-job-posting";
    }

    @PostMapping("/job-posting/{id}/edit")
    public String updateJobPosting(@PathVariable Long id, @ModelAttribute JobPosting updatedJobPosting) {
        JobPosting existingJobPosting = jobPostingService.getJobPostingById(id);
        existingJobPosting.setJobTitle(updatedJobPosting.getJobTitle());
        existingJobPosting.setLocation(updatedJobPosting.getLocation());
        existingJobPosting.setSalary(updatedJobPosting.getSalary());
        existingJobPosting.setDescription(updatedJobPosting.getDescription());
        
        jobPostingService.updateJobPosting(existingJobPosting);
        
        return "redirect:/employer/job-posting/" + id;
    }
}
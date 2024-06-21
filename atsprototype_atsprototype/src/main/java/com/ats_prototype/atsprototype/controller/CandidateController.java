package com.ats_prototype.atsprototype.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ats_prototype.atsprototype.entity.Candidate;
import com.ats_prototype.atsprototype.entity.JobPosting;
import com.ats_prototype.atsprototype.service.CandidateService;
import com.ats_prototype.atsprototype.service.JobPostingService;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

	private final CandidateService candidateService;
	private final JobPostingService jobPostingService;
	public CandidateController(CandidateService candidateService, JobPostingService jobPostingService) {
		this.candidateService = candidateService;
		this.jobPostingService = jobPostingService;
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    Candidate candidate = new Candidate(null, "", "", "", "");
	    model.addAttribute("candidate", candidate);
	    return "candidate-registration";
	}

    @PostMapping("/register")
    public String registerCandidate(Candidate candidate) {
        candidateService.registerCandidate(candidate);
        return "login";
    }
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Get the authenticated user's email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Fetch the candidate details from the database using the email
        Candidate candidate = candidateService.findCandidateByEmail(email);

        // Add candidate details to the model
        model.addAttribute("candidate", candidate);

        // Return the candidate dashboard template
        return "candidate-dashboard";
    }

    @GetMapping("/jobs/job-listing")
    public String showJobList(Model model) {
        List<JobPosting> jobPostings = jobPostingService.getAllJobPostings();
        model.addAttribute("jobPostings", jobPostings);
        return "job-listing";
    }
    
    @GetMapping("/apply")
    public String showApplicationForm(Model model) {
        // Implement logic to fetch job posting details and R1 check form
        return "apply-for-job";
    }

    @PostMapping("/apply")
    public String submitApplication() {
        // Implement logic to save candidate application and R1 check form responses
        return "redirect:/candidate-dashboard";
    }
}

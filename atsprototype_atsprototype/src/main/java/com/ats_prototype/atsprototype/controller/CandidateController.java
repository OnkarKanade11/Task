package com.ats_prototype.atsprototype.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ats_prototype.atsprototype.entity.Candidate;
import com.ats_prototype.atsprototype.entity.JobApplication;
import com.ats_prototype.atsprototype.entity.JobPosting;
import com.ats_prototype.atsprototype.service.CandidateService;
import com.ats_prototype.atsprototype.service.JobPostingService;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private JobPostingService jobPostingService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        Candidate candidate = candidateService.findCandidateByEmail(principal.getName());
        model.addAttribute("candidate", candidate);
        return "candidate-dashboard";
    }

    @GetMapping("/jobs")
    public String viewJobPostings(Model model) {
        List<JobPosting> jobPostings = jobPostingService.getAllJobPostings();
        model.addAttribute("jobPostings", jobPostings);
        return "job-listings";
    }

    @GetMapping("/apply/{jobId}")
    public String showApplicationForm(@PathVariable Long jobId, Model model) {
        JobPosting jobPosting = jobPostingService.getJobPostingById(jobId);
        model.addAttribute("jobPosting", jobPosting);
        model.addAttribute("r1CheckQuestions", jobPosting.getR1CheckQuestions());
        return "job-application-form";
    }

    @PostMapping("/apply/{jobId}")
    public String applyForJob(@PathVariable Long jobId, @RequestParam Map<String, String> r1CheckResponses, 
                              @RequestParam("resume") MultipartFile resume, Principal principal) throws IOException {
        Candidate candidate = candidateService.findCandidateByEmail(principal.getName());
        candidateService.uploadResume(candidate.getId(), resume.getBytes());
        candidateService.applyForJob(candidate.getId(), jobId, r1CheckResponses);
        return "redirect:/candidate/applications";
    }

    @GetMapping("/applications")
    public String viewApplications(Model model, Principal principal) {
        Candidate candidate = candidateService.findCandidateByEmail(principal.getName());
        List<JobApplication> applications = candidateService.getCandidateApplications(candidate.getId());
        model.addAttribute("applications", applications);
        return "candidate-applications";
    }
}
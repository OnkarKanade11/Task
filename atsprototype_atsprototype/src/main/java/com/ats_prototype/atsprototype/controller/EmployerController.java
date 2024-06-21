package com.ats_prototype.atsprototype.controller;

 
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
 private final CandidateService candidateService;

 public EmployerController(EmployerService employerService, JobPostingService jobPostingService, CandidateService candidateService) {
     this.employerService = employerService;
     this.jobPostingService = jobPostingService;
     this.candidateService = candidateService;
 }

 @GetMapping("/dashboard")
 public String showEmployerDashboard(Model model, Principal principal) {
     // Get the logged-in employer's email from the Principal
     String employerEmail = principal.getName();

     // Fetch the employer object from the database
     Employer employer = employerService.getEmployerByEmail(employerEmail);

     // Fetch all job postings created by the employer
     List<JobPosting> jobPostings = jobPostingService.getJobPostingsByEmployer(employer);
     model.addAttribute("jobPostings", jobPostings);

  // Fetch shortlisted candidates for the employer
     List<Candidate> shortlistedCandidates = candidateService.getShortlistedCandidatesByEmployer(employer);
     model.addAttribute("shortlistedCandidates", shortlistedCandidates);
     
     return "employer-dashboard";
 }

	 @GetMapping("/job-posting")
	 public String showJobPostingForm(Model model) {
	     JobPosting jobPosting = new JobPosting(null, null, null, null, null, null, null, null, null);
	     List<Question> r1CheckQuestions = new ArrayList<>();
	     for (int i = 0; i < 5; i++) {
	         r1CheckQuestions.add(new Question(null, null, null));
	     }
	     jobPosting.setR1CheckQuestions(r1CheckQuestions);
	     model.addAttribute("jobPosting", jobPosting);
	     return "edit-job-posting";
	 }
	
	 @PostMapping("/job-posting")
	 public String createJobPosting(@ModelAttribute("jobPosting") JobPosting jobPosting) {
	     // Set the employer for the job posting (assuming you have a logged-in employer)
	     Employer employer = new Employer(null, null, null, null, null); // Replace with the actual logged-in employer object
	     jobPosting.setEmployer(employer);
	
	     // Save the job posting to the database
	     jobPostingService.createJobPosting(jobPosting);
	
	     // Redirect to the employer dashboard or a success page
	     return "redirect:/employer/dashboard";
	 }


 // Other methods for managing job postings and reviewing shortlisted candidates
}

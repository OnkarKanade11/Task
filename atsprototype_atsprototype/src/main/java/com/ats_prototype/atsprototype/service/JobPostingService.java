package com.ats_prototype.atsprototype.service;

 
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ats_prototype.atsprototype.entity.Employer;
import com.ats_prototype.atsprototype.entity.JobPosting;
import com.ats_prototype.atsprototype.repository.JobPostingRepository;

@Service
public class JobPostingService {

	private final JobPostingRepository jobPostingRepository;

    public JobPostingService(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }

    public void createJobPosting(JobPosting jobPosting) {
        jobPostingRepository.save(jobPosting);
    }

    public List<JobPosting> getAllJobPostings() {
        return jobPostingRepository.findAll();
    }

    public JobPosting getJobPostingById(Long id) {
        Optional<JobPosting> optionalJobPosting = jobPostingRepository.findById(id);
        return optionalJobPosting.orElse(null);
    }

    public void updateJobPosting(JobPosting jobPosting) {
        jobPostingRepository.save(jobPosting);
    }
    
    public List<JobPosting> getJobPostingsByEmployer(Employer employer) {
        return jobPostingRepository.findByEmployer(employer);
    }

 // Other methods for managing job posting-related operations
}
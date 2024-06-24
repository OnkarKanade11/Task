package com.ats_prototype.atsprototype.service;

 
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ats_prototype.atsprototype.entity.Employer;
import com.ats_prototype.atsprototype.entity.JobPosting;
import com.ats_prototype.atsprototype.entity.Question;
import com.ats_prototype.atsprototype.entity.Recruiter;
import com.ats_prototype.atsprototype.repository.JobPostingRepository;
import com.ats_prototype.atsprototype.repository.RecruiterRepository;

@Service
public class JobPostingService {

	private final JobPostingRepository jobPostingRepository;
	private final RecruiterRepository recruiterRepository;

    public JobPostingService(JobPostingRepository jobPostingRepository,RecruiterRepository recruiterRepository) {
        this.jobPostingRepository = jobPostingRepository;
        this.recruiterRepository = recruiterRepository;
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

    public void assignRecruiterToJobPosting(Long jobPostingId, Long recruiterId) {
        Optional<JobPosting> jobPostingOptional = jobPostingRepository.findById(jobPostingId);
        Optional<Recruiter> recruiterOptional = recruiterRepository.findById(recruiterId);

        if (jobPostingOptional.isPresent() && recruiterOptional.isPresent()) {
            JobPosting jobPosting = jobPostingOptional.get();
            Recruiter recruiter = recruiterOptional.get();
            jobPosting.setRecruiter(recruiter);
            jobPostingRepository.save(jobPosting);
        }
    }

    public void addR2CheckQuestions(Long jobPostingId, List<String> r2Questions) {
        Optional<JobPosting> jobPostingOptional = jobPostingRepository.findById(jobPostingId);
        
        if (jobPostingOptional.isPresent()) {
            JobPosting jobPosting = jobPostingOptional.get();
            for (String questionText : r2Questions) {
                Question question = new Question();
                question.setQuestion(questionText);
                jobPosting.addR2CheckQuestion(question);
            }
            jobPostingRepository.save(jobPosting);
        }
    }
 // Other methods for managing job posting-related operations
}
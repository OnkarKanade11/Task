package com.ats_prototype.atsprototype.service;

import java.util.List;
import java.util.Map;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats_prototype.atsprototype.entity.Candidate;
import com.ats_prototype.atsprototype.entity.JobApplication;
import com.ats_prototype.atsprototype.repository.CandidateRepository;
import com.ats_prototype.atsprototype.repository.JobApplicationRepository;
import com.ats_prototype.atsprototype.repository.JobPostingRepository;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    private JobPostingRepository jobPostingRepository;

    public Candidate findCandidateByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    public void uploadResume(Long candidateId, byte[] resumeData) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));
        candidate.setResume(resumeData);
        candidateRepository.save(candidate);
    }

    public void applyForJob(Long candidateId, Long jobPostingId, Map<String, String> r1CheckResponses) {
        JobApplication application = new JobApplication();
        application.setCandidate(candidateRepository.findById(candidateId).orElseThrow());
        application.setJobPosting(jobPostingRepository.findById(jobPostingId).orElseThrow());
        application.setStatus("Applied");
        application.setR1CheckResponses(r1CheckResponses);
        jobApplicationRepository.save(application);
    }

    public List<JobApplication> getCandidateApplications(Long candidateId) {
        return jobApplicationRepository.findByCandidateId(candidateId);
    }
}
package com.ats_prototype.atsprototype.entity;

import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class JobApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Candidate candidate;

    @ManyToOne
    private JobPosting jobPosting;

    @ManyToOne
    private Employer employer;

    private String status;
    
    @ElementCollection
    private Map<String, String> r2CheckResponses;

    

	@ElementCollection
    private Map<String, String> r1CheckResponses;

    // No-argument constructor
    public JobApplication() {
    }

    // All-arguments constructor
    public JobApplication(Long id, Candidate candidate, JobPosting jobPosting, Employer employer, String status) {
        this.id = id;
        this.candidate = candidate;
        this.jobPosting = jobPosting;
        this.employer = employer;
        this.status = status;
       
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getR1CheckResponses() {
        return r1CheckResponses;
    }

    public void setR1CheckResponses(Map<String, String> r1CheckResponses) {
        this.r1CheckResponses = r1CheckResponses;
    }
}

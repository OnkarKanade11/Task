package com.ats_prototype.atsprototype.service;

 
import org.springframework.stereotype.Service;

import com.ats_prototype.atsprototype.entity.Recruiter;
import com.ats_prototype.atsprototype.repository.RecruiterRepository;

@Service
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;

    public RecruiterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    public void registerRecruiter(Recruiter recruiter) {
        recruiterRepository.save(recruiter);
    }

 // Other methods for managing recruiter-related operations
}

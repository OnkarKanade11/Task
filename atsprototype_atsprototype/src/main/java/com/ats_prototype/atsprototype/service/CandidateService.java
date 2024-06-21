package com.ats_prototype.atsprototype.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ats_prototype.atsprototype.entity.Candidate;
import com.ats_prototype.atsprototype.entity.Employer;
import com.ats_prototype.atsprototype.repository.CandidateRepository;

@Service
public class CandidateService {

	private final CandidateRepository candidateRepository;
    private static final Logger logger = LoggerFactory.getLogger(CandidateService.class);

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public void registerCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    public Candidate findCandidateByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }
    
    public List<Candidate> getShortlistedCandidatesByEmployer(Employer employer) {
        return candidateRepository.findShortlistedCandidatesByEmployer(employer);
    }
}

package com.ats_prototype.atsprototype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats_prototype.atsprototype.entity.Candidate;
import com.ats_prototype.atsprototype.entity.Coordinator;
import com.ats_prototype.atsprototype.entity.Employer;
import com.ats_prototype.atsprototype.entity.Recruiter;
import com.ats_prototype.atsprototype.entity.User;
import com.ats_prototype.atsprototype.repository.CandidateRepository;
import com.ats_prototype.atsprototype.repository.CoordinatorRepository;
import com.ats_prototype.atsprototype.repository.EmployerRepository;
import com.ats_prototype.atsprototype.repository.RecruiterRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private CoordinatorRepository coordinatorRepository;

    @Transactional
    public void registerUser(User user) {
        switch (user.getRole()) {
            case "ROLE_CANDIDATE":
                Candidate candidate = new Candidate();
                candidate.setEmail(user.getEmail());
                candidate.setPassword(user.getPassword());
                candidate.setRole(user.getRole());
                candidate.setFirstname(user.getFirstname());
                candidate.setLastname(user.getLastname());
                candidateRepository.save(candidate);
                break;
            case "ROLE_RECRUITER":
                Recruiter recruiter = new Recruiter(user.getEmail(), user.getPassword(), user.getRole());
                recruiter.setFirstname(user.getFirstname());
                recruiter.setLastname(user.getLastname());
                recruiterRepository.save(recruiter);
                break;
            case "ROLE_EMPLOYER":
                Employer employer = new Employer(user.getEmail(), user.getPassword(), user.getRole());
                employer.setCompanyName(user.getCompanyName());
                employer.setContactName(user.getContactName());
                employerRepository.save(employer);
                break;
            case "ROLE_COORDINATOR":
                Coordinator coordinator = new Coordinator(user.getEmail(), user.getPassword(), user.getRole());
                coordinator.setFirstname(user.getFirstname());
                coordinator.setLastname(user.getLastname());
                coordinatorRepository.save(coordinator);
                break;
        }
    }
}
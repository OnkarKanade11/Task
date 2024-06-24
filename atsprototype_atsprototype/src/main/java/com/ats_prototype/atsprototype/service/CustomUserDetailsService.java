package com.ats_prototype.atsprototype.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ats_prototype.atsprototype.entity.Candidate;
import com.ats_prototype.atsprototype.entity.Coordinator;
import com.ats_prototype.atsprototype.entity.Employer;
import com.ats_prototype.atsprototype.entity.Recruiter;
import com.ats_prototype.atsprototype.repository.CandidateRepository;
import com.ats_prototype.atsprototype.repository.CoordinatorRepository;
import com.ats_prototype.atsprototype.repository.EmployerRepository;
import com.ats_prototype.atsprototype.repository.RecruiterRepository;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final CandidateRepository candidateRepository;
    private final EmployerRepository employerRepository;
    private final RecruiterRepository recruiterRepository;
    private final CoordinatorRepository coordinatorRepository;

    public CustomUserDetailsService(CandidateRepository candidateRepository,
                                    EmployerRepository employerRepository,
                                    RecruiterRepository recruiterRepository,
                                    CoordinatorRepository coordinatorRepository) {
        this.candidateRepository = candidateRepository;
        this.employerRepository = employerRepository;
        this.recruiterRepository = recruiterRepository;
        this.coordinatorRepository = coordinatorRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.debug("Attempting to load user by email: {}", email);

        UserDetails userDetails = loadCandidate(email);
        if (userDetails == null) userDetails = loadEmployer(email);
        if (userDetails == null) userDetails = loadRecruiter(email);
        if (userDetails == null) userDetails = loadCoordinator(email);

        if (userDetails == null) {
            logger.warn("User not found with email: {}", email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        logger.debug("User loaded successfully: {}", email);
        return userDetails;
    }

    private UserDetails loadCandidate(String email) {
        Candidate candidate = candidateRepository.findByEmail(email);
        return candidate != null ? createUserDetails(candidate.getEmail(), candidate.getPassword(), "ROLE_CANDIDATE") : null;
    }

    private UserDetails loadEmployer(String email) {
        Employer employer = employerRepository.findByEmail(email);
        return employer != null ? createUserDetails(employer.getEmail(), employer.getPassword(), "ROLE_EMPLOYER") : null;
    }

    private UserDetails loadRecruiter(String email) {
        Recruiter recruiter = recruiterRepository.findByEmail(email);
        return recruiter != null ? createUserDetails(recruiter.getEmail(), recruiter.getPassword(), "ROLE_RECRUITER") : null;
    }

    private UserDetails loadCoordinator(String email) {
        Coordinator coordinator = coordinatorRepository.findByEmail(email);
        return coordinator != null ? createUserDetails(coordinator.getEmail(), coordinator.getPassword(), "ROLE_COORDINATOR") : null;
    }

    private UserDetails createUserDetails(String email, String password, String role) {
        return User.builder()
                .username(email)
                .password(password)
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(role)))
                .build();
    }
}
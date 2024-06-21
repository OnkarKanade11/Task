package com.ats_prototype.atsprototype.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

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

    public CustomUserDetailsService(CandidateRepository candidateRepository, EmployerRepository employerRepository, RecruiterRepository recruiterRepository, CoordinatorRepository coordinatorRepository) {
        this.candidateRepository = candidateRepository;
        this.employerRepository = employerRepository;
        this.recruiterRepository = recruiterRepository;
        this.coordinatorRepository = coordinatorRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Candidate candidate = candidateRepository.findByEmail(email);
        if (candidate != null) {
            return new org.springframework.security.core.userdetails.User(
                candidate.getEmail(), "{noop}" + candidate.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_CANDIDATE"))
            );
        }

        Employer employer = employerRepository.findByEmail(email);
        if (employer != null) {
            return new org.springframework.security.core.userdetails.User(
                employer.getEmail(), "{noop}" + employer.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_EMPLOYER"))
            );
        }

        Recruiter recruiter = recruiterRepository.findByEmail(email);
        if (recruiter != null) {
            return new org.springframework.security.core.userdetails.User(
                recruiter.getEmail(), "{noop}" + recruiter.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_RECRUITER"))
            );
        }

        Coordinator coordinator = coordinatorRepository.findByEmail(email);
        if (coordinator != null) {
            return new org.springframework.security.core.userdetails.User(
                coordinator.getEmail(), "{noop}" + coordinator.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_COORDINATOR"))
            );
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}

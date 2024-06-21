package com.ats_prototype.atsprototype.service;

 
import org.springframework.stereotype.Service;

import com.ats_prototype.atsprototype.entity.Employer;
import com.ats_prototype.atsprototype.repository.EmployerRepository;

@Service
public class EmployerService {

	private final EmployerRepository employerRepository;

    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public void registerEmployer(Employer employer) {
        employerRepository.save(employer);
    }

    public Employer getEmployerByEmail(String email) {
        return employerRepository.findByEmail(email);
    }

 // Other methods for managing employer-related operations
}

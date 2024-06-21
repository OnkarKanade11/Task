package com.ats_prototype.atsprototype.repository;

import org.springframework.stereotype.Repository;

import com.ats_prototype.atsprototype.entity.Employer;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository

public interface EmployerRepository extends JpaRepository <Employer, Long>{

	Employer findByEmail(String email);
}

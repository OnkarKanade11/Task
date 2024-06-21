package com.ats_prototype.atsprototype.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats_prototype.atsprototype.entity.Employer;
import com.ats_prototype.atsprototype.entity.JobPosting;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
   Optional<JobPosting> findById(Long id);

List<JobPosting> findByEmployer(Employer employer);
	 
}

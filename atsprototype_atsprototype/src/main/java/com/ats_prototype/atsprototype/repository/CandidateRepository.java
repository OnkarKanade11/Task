package com.ats_prototype.atsprototype.repository;

import org.springframework.stereotype.Repository;

import com.ats_prototype.atsprototype.entity.Candidate;
import com.ats_prototype.atsprototype.entity.Employer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CandidateRepository extends JpaRepository <Candidate, Long>{

	Candidate findByEmail(String email);

    @Query("SELECT c FROM Candidate c JOIN c.jobApplications ja WHERE ja.employer = :employer AND ja.status = 'Shortlisted'")
    List<Candidate> findShortlistedCandidatesByEmployer(@Param("employer") Employer employer);
}

package com.ats_prototype.atsprototype.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats_prototype.atsprototype.entity.JobApplication;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByCandidateId(Long candidateId);
    List<JobApplication> findByJobPostingId(Long jobPostingId);
    List<JobApplication> findByJobPostingIdAndStatus(Long jobPostingId, String status);
    JobApplication findFirstByStatusOrderByIdAsc(String status);
}
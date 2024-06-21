package com.ats_prototype.atsprototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats_prototype.atsprototype.entity.Coordinator;
@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
    Coordinator findByEmail(String email);
}
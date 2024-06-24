package com.ats_prototype.atsprototype.service;

import com.ats_prototype.atsprototype.entity.JobApplication;
import com.ats_prototype.atsprototype.repository.JobApplicationRepository;
import com.ats_prototype.atsprototype.service.JobApplicationService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Override
    public JobApplication getApplicationById(Long applicationId) {
        return jobApplicationRepository.findById(applicationId)
            .orElseThrow(() -> new RuntimeException("Application not found"));
    }

    @Override
    @Transactional
    public void submitR2CheckAndShortlist(Long applicationId, Map<String, String> r2Responses, boolean shortlist) {
        JobApplication application = getApplicationById(applicationId);
        
        // Save R2 check responses
        application.setR2CheckResponses(r2Responses);
        
        // Update status based on shortlist decision
        if (shortlist) {
            application.setStatus("SHORTLISTED");
        } else {
            application.setStatus("R2_COMPLETED");
        }
        
        jobApplicationRepository.save(application);
    }
}
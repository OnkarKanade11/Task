package com.ats_prototype.atsprototype.service;

import com.ats_prototype.atsprototype.entity.JobApplication;
 

import java.util.Map;

public interface JobApplicationService {
	JobApplication getApplicationById(Long applicationId);
    void submitR2CheckAndShortlist(Long applicationId, Map<String, String> r2Responses, boolean shortlist);
}
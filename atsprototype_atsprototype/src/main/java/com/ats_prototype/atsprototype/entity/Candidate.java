package com.ats_prototype.atsprototype.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Candidate extends User {

	@Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] resume;
	
	public byte[] getResume() {
		return resume;
	}

	public void setResume(byte[] resume) {
		this.resume = resume;
	}

	@OneToMany(mappedBy = "candidate")
    private List<JobApplication> jobApplications;
	
	
	public Candidate() {
        super();
    }

    public Candidate(String email, String password, String role) {
        super(email, password, role);
    }
	
	
	public List<JobApplication> getJobApplications() {
		return jobApplications;
	}
	
	public void setJobApplications(List<JobApplication> jobApplications) {
		this.jobApplications = jobApplications;
	}
	
	 
	
}

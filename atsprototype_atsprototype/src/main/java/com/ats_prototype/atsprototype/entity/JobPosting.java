package com.ats_prototype.atsprototype.entity;

import java.util.List;

 

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class JobPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String jobTitle;
	private String location;
	private Double salary;
	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Question> r1CheckQuestions;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Question> r2CheckQuestions;

	@ManyToOne
	private Employer employer;
	
	@ManyToOne
	private Recruiter recruiter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Question> getR1CheckQuestions() {
		return r1CheckQuestions;
	}

	public void setR1CheckQuestions(List<Question> r1CheckQuestions) {
		this.r1CheckQuestions = r1CheckQuestions;
	}

	public List<Question> getR2CheckQuestions() {
		return r2CheckQuestions;
	}

	public void setR2CheckQuestions(List<Question> r2CheckQuestions) {
		this.r2CheckQuestions = r2CheckQuestions;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

	public JobPosting(Long id, String jobTitle, String location, Double salary, String description,
			List<Question> r1CheckQuestions, List<Question> r2CheckQuestions, Employer employer, Recruiter recruiter) {
		super();
		this.id = id;
		this.jobTitle = jobTitle;
		this.location = location;
		this.salary = salary;
		this.description = description;
		this.r1CheckQuestions = r1CheckQuestions;
		this.r2CheckQuestions = r2CheckQuestions;
		this.employer = employer;
		this.recruiter = recruiter;
	}
	
	
	

}

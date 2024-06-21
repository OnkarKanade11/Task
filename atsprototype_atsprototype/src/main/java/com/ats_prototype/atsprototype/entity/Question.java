package com.ats_prototype.atsprototype.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.lang.Boolean;

@Entity
public class Question {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private Boolean correctAnswer;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Boolean getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(Boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public Question(Long id, String question, Boolean correctAnswer) {
		super();
		this.id = id;
		this.question = question;
		this.correctAnswer = correctAnswer;
	}
    
    
}

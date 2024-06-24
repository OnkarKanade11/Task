package com.ats_prototype.atsprototype.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employer extends User {

	public Employer() {
        super();
    }

    public Employer(String email, String password, String role) {
        super(email, password, role);
    }

}

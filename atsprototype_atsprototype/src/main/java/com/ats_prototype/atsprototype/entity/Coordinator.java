package com.ats_prototype.atsprototype.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Coordinator extends User {

	public Coordinator() {
        super();
    }

    public Coordinator(String email, String password, String role) {
        super(email, password, role);
    }
    
    
  
}    

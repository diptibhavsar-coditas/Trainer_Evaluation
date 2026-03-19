package com.example.OneToOne.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bloodGroup;
    private String allergies;
    private String pastDiseases;
    private String currentMedication;

    @OneToOne(mappedBy = "medicalHistory")
    private Patient patient;

    // getters & setters
}
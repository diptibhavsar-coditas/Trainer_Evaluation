package com.example.OneToOne.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String gender;
    private String mobile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_history_id")
    private MedicalHistory medicalHistory;

    // getters & setters
}

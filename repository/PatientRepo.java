package com.example.OneToOne.repository;

import com.example.OneToOne.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient,Long> {

    List<Patient> findByMedicalHistory_BloodGroup(String bloodGroup);
}

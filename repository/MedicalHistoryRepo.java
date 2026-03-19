package com.example.OneToOne.repository;

import com.example.OneToOne.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalHistoryRepo extends JpaRepository<MedicalHistory,Long> {
}

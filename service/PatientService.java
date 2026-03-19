package com.example.OneToOne.service;
import com.example.OneToOne.entity.MedicalHistory;
import com.example.OneToOne.entity.Patient;
import com.example.OneToOne.exception.ResourceNotFoundException;
import com.example.OneToOne.repository.MedicalHistoryRepo;
import com.example.OneToOne.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private MedicalHistoryRepo medicalRepo;

    // 1. Register Patient
    public Patient save(Patient patient) {
        return patientRepo.save(patient);
    }

    // 2. Get All Patients
    public List<Patient> getAll() {
        return patientRepo.findAll();
    }

    // 3. Get Patient by ID
    public Patient getById(Long id) {
        return patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
    }

    // 4. Get Medical History
    public MedicalHistory getMedicalHistory(Long id) {
        Patient p = getById(id);
        return p.getMedicalHistory();
    }

    // 5. Update Patient
    public Patient updatePatient(Long id, Patient updated) {
        Patient p = getById(id);
        p.setName(updated.getName());
        p.setAge(updated.getAge());
        p.setMobile(updated.getMobile());
        return patientRepo.save(p);
    }

    // 6. Update Medical History
    public MedicalHistory updateMedical(Long id, MedicalHistory m) {
        Patient p = getById(id);
        MedicalHistory existing = p.getMedicalHistory();

        existing.setAllergies(m.getAllergies());
        existing.setPastDiseases(m.getPastDiseases());
        existing.setCurrentMedication(m.getCurrentMedication());

        return medicalRepo.save(existing);
    }

    // 7. Delete Patient (cascade auto delete)
    public void delete(Long id) {
        patientRepo.deleteById(id);
    }

    // 8. Get All Medical Histories
    public List<MedicalHistory> getAllMedical() {
        return medicalRepo.findAll();
    }

    // 9. Add Medical History later
    public Patient addMedical(Long id, MedicalHistory m) {
        Patient p = getById(id);
        p.setMedicalHistory(m);
        return patientRepo.save(p);
    }

    // 10. Find by Blood Group
    public List<Patient> findByBlood(String blood) {
        return patientRepo.findByMedicalHistory_BloodGroup(blood);
    }
}
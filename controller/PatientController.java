package com.example.OneToOne.controller;

import com.example.OneToOne.entity.MedicalHistory;
import com.example.OneToOne.entity.Patient;
import com.example.OneToOne.service.PatientService;
import com.example.OneToOne.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    // 1
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Patient p) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Patient created", service.save(p))
        );
    }

    // 2
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "All patients", service.getAll())
        );
    }

    // 3
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Patient found", service.getById(id))
        );
    }

    // 4
    @GetMapping("/{id}/medical")
    public ResponseEntity<?> getMedical(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Medical history", service.getMedicalHistory(id))
        );
    }

    // 5
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Patient p) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Patient updated", service.updatePatient(id, p))
        );
    }

    // 6
    @PutMapping("/{id}/medical")
    public ResponseEntity<?> updateMedical(@PathVariable Long id, @RequestBody MedicalHistory m) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Medical updated", service.updateMedical(id, m))
        );
    }

    // 7
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Patient deleted", null)
        );
    }

    // 8
    @GetMapping("/medical/all")
    public ResponseEntity<?> getAllMedical() {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "All medical records", service.getAllMedical())
        );
    }

    // 9
    @PostMapping("/{id}/medical")
    public ResponseEntity<?> addMedical(@PathVariable Long id, @RequestBody MedicalHistory m) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Medical added", service.addMedical(id, m))
        );
    }

    // 10
    @GetMapping("/search")
    public ResponseEntity<?> findByBlood(@RequestParam String bloodGroup) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Filtered patients", service.findByBlood(bloodGroup))
        );
    }
}

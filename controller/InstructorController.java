package com.example.week7.controller;

import com.example.week7.entity.Instructor;
import com.example.week7.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService service;

    public InstructorController(InstructorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Instructor> create(@RequestBody @Valid Instructor instructor) {
        return ResponseEntity.ok(service.createInstructor(instructor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getInstructor(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> update(@PathVariable Long id,
                                             @RequestBody Instructor instructor) {
        return ResponseEntity.ok(service.updateInstructor(id, instructor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteInstructor(id);
        return ResponseEntity.ok("Instructor deleted");
    }

    @DeleteMapping("/{id}/profile")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        service.deleteInstructorProfile(id);
        return ResponseEntity.ok("Profile deleted only");
    }
}
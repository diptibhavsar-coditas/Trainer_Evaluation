package com.example.week7.service;

import com.example.week7.entity.Instructor;
import com.example.week7.repo.InstructorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class InstructorService {

    private final InstructorRepository instructorRepo;

    public InstructorService(InstructorRepository instructorRepo) {
        this.instructorRepo = instructorRepo;
    }

    public Instructor createInstructor(Instructor instructor) {

        // Set bidirectional mapping
        if (instructor.getProfile() != null) {
            instructor.getProfile().setInstructor(instructor);
        }

        if (instructor.getCourses() != null) {
            instructor.getCourses().forEach(c -> c.setInstructor(instructor));
        }

        return instructorRepo.save(instructor);
    }

    public Instructor getInstructor(Long id) {
        return instructorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
    }

    public Instructor updateInstructor(Long id, Instructor updated) {
        Instructor existing = getInstructor(id);

        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());

        return instructorRepo.save(existing);
    }

    public void deleteInstructor(Long id) {
        instructorRepo.deleteById(id);
    }

    public void deleteInstructorProfile(Long instructorId) {
        Instructor instructor = getInstructor(instructorId);
        instructor.setProfile(null);
    }
}
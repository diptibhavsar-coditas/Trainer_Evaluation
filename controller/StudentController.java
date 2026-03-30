package com.example.week7.controller;

import com.example.week7.entity.Student;
import com.example.week7.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.ok(service.createStudent(student));
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<Student> enroll(@PathVariable Long studentId,
                                          @PathVariable Long courseId) {
        return ResponseEntity.ok(service.enrollStudent(studentId, courseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStudent(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.ok("Student deleted");
    }
}
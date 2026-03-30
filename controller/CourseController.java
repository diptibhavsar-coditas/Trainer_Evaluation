package com.example.week7.controller;

import com.example.week7.entity.Course;
import com.example.week7.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Course> create(@PathVariable Long id,
                                         @RequestBody Course course) {
        return ResponseEntity.ok(service.createCourse(id, course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCourse(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id,
                                         @RequestBody Course course) {
        return ResponseEntity.ok(service.updateCourse(id, course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteCourse(id);
        return ResponseEntity.ok("Course deleted");
    }
}
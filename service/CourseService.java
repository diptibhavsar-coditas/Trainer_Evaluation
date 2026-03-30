package com.example.week7.service;

import com.example.week7.entity.Course;
import com.example.week7.entity.Instructor;
import com.example.week7.repo.CourseRepository;
import com.example.week7.repo.InstructorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepo;
    private final InstructorRepository instructorRepo;

    public CourseService(CourseRepository courseRepo, InstructorRepository instructorRepo) {
        this.courseRepo = courseRepo;
        this.instructorRepo = instructorRepo;
    }

    public Course createCourse(Long instructorId, Course course) {

        Instructor instructor = instructorRepo.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        course.setInstructor(instructor);

        return courseRepo.save(course);
    }

    public Course getCourse(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course updateCourse(Long id, Course updated) {
        Course course = getCourse(id);
        course.setTitle(updated.getTitle());
        return courseRepo.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }
}
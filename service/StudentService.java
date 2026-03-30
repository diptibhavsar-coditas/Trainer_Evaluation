package com.example.week7.service;

import com.example.week7.entity.Course;
import com.example.week7.entity.Student;
import com.example.week7.repo.CourseRepository;
import com.example.week7.repo.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public StudentService(StudentRepository studentRepo, CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student enrollStudent(Long studentId, Long courseId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);
        course.getStudents().add(student);

        return studentRepo.save(student);
    }

    public Student getStudent(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }
}
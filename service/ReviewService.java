package com.example.week7.service;

import com.example.week7.entity.Course;
import com.example.week7.entity.Review;
import com.example.week7.repo.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReviewService {

    private final CourseRepository courseRepo;

    public ReviewService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Course addReview(Long courseId, Review review) {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        review.setCourse(course);
        course.getReviews().add(review);

        return courseRepo.save(course);
    }

    public Course getCourseWithReviews(Long courseId) {
        return courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }
}
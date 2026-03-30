package com.example.week7.controller;

import com.example.week7.entity.Course;
import com.example.week7.entity.Review;
import com.example.week7.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity< Course> addReview(@PathVariable Long courseId,
                                            @RequestBody Review review) {
        return ResponseEntity.ok(service.addReview(courseId, review));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Course> getCourseReviews(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getCourseWithReviews(courseId));
    }
}
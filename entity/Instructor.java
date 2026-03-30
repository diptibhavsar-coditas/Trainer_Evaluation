package com.example.week7.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToOne(mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonBackReference
    private InstructorProfile profile;

    @OneToMany(mappedBy = "instructor",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("course")
    private List<Course> courses = new ArrayList<>();

    // Helper method
    public void addCourse(Course course) {
        courses.add(course);
        course.setInstructor(this);
    }
}
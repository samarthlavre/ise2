package com.example.catalog.service;

import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.*;
import com.example.catalog.model.Course;
import com.example.catalog.model.Instructor;

@Service
public class CatalogService {
    private final Map<Long, Course> courses = new HashMap<>();
    private final Map<Long, Instructor> instructors = new HashMap<>();

    @PostConstruct
    public void init() {
        // seed instructors
        instructors.put(1L, new Instructor(1L, "Alice Smith", "Senior Java instructor", "alice@example.com"));
        instructors.put(2L, new Instructor(2L, "Bob Jones", "Frontend specialist", "bob@example.com"));

        // seed courses
        courses.put(101L, new Course(101L, "Intro to Java", "Basics of Java", 
            "Comprehensive Java fundamentals including OOP and collections.", 49.99, 1L));
        courses.put(102L, new Course(102L, "Spring Boot Basics", "Build REST APIs", 
            "Learn Spring Boot to create production-ready REST services.", 79.99, 1L));
        courses.put(201L, new Course(201L, "HTML & CSS", "Web fundamentals", 
            "Build responsive pages using modern HTML and CSS.", 29.99, 2L));
        courses.put(202L, new Course(202L, "JavaScript Essentials", "DOM & ES6", 
            "Core JavaScript concepts, DOM manipulation and ES6 features.", 39.99, 2L));
    }

    public List<Course> findAllCourses() { return new ArrayList<>(courses.values()); }
    public Optional<Course> findCourseById(Long id) { return Optional.ofNullable(courses.get(id)); }
    public Optional<Instructor> findInstructorById(Long id) { return Optional.ofNullable(instructors.get(id)); }
    public List<Course> findCoursesByInstructorId(Long instructorId) {
        List<Course> result = new ArrayList<>();
        for (Course c : courses.values()) {
            if (Objects.equals(c.getInstructorId(), instructorId)) result.add(c);
        }
        return result;
    }
}

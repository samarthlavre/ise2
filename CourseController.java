package com.example.catalog.controller;

import com.example.catalog.model.Course;
import com.example.catalog.model.Instructor;
import com.example.catalog.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {
    private final CatalogService catalogService;

    public CourseController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("courses", catalogService.findAllCourses());
        return "index";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", catalogService.findAllCourses());
        return "courses";
    }

    @GetMapping("/courses/{id}")
    public String courseDetails(@PathVariable Long id, Model model) {
        Course course = catalogService.findCourseById(id).orElse(null);
        if (course == null) return "redirect:/courses";
        model.addAttribute("course", course);
        model.addAttribute("instructor", catalogService.findInstructorById(course.getInstructorId()).orElse(null));
        return "course";
    }

    @GetMapping("/instructors/{id}")
    public String instructor(@PathVariable Long id, Model model) {
        Instructor instructor = catalogService.findInstructorById(id).orElse(null);
        if (instructor == null) return "redirect:/courses";
        model.addAttribute("instructor", instructor);
        model.addAttribute("courses", catalogService.findCoursesByInstructorId(id));
        return "instructor";
    }

    @GetMapping("/enroll/{courseId}")
    public String enrollForm(@PathVariable Long courseId, Model model) {
        Course course = catalogService.findCourseById(courseId).orElse(null);
        if (course == null) return "redirect:/courses";
        model.addAttribute("course", course);
        return "enroll";
    }

    @PostMapping("/enroll")
    public String enrollSubmit(@RequestParam Long courseId, @RequestParam String name, @RequestParam String email, Model model) {
        Course course = catalogService.findCourseById(courseId).orElse(null);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("course", course);
        // NOTE: no DB; in real app you'd save enrollment
        return "enroll-success";
    }
}

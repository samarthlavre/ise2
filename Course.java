package com.example.catalog.model;

public class Course {
    private Long id;
    private String title;
    private String shortDescription;
    private String description;
    private Double price;
    private Long instructorId;

    public Course() {}
    public Course(Long id, String title, String shortDescription, String description, Double price, Long instructorId) {
        this.id = id; this.title = title; this.shortDescription = shortDescription;
        this.description = description; this.price = price; this.instructorId = instructorId;
    }
    // getters & setters
    // (omitted here for brevity — include usual getters/setters)
}

package com.example.catalog.model;

public class Instructor {
    private Long id;
    private String name;
    private String bio;
    private String email;

    public Instructor() {}
    public Instructor(Long id, String name, String bio, String email) {
        this.id = id; this.name = name; this.bio = bio; this.email = email;
    }
    // getters & setters
}

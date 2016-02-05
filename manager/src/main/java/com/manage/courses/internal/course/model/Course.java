package com.manage.courses.internal.course.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Course {
    public Course() {
        this.courseId = UUID.randomUUID().toString();
    }
    private String courseId;
    private String name;
    private String description;
    private int maxScore;
}

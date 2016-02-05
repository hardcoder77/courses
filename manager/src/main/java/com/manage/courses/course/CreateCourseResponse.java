package com.manage.courses.course;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

@Data
@JsonSnakeCase
public class CreateCourseResponse {
    private String courseId;
    private String name;
    private String description;
    private int maxScore;
}

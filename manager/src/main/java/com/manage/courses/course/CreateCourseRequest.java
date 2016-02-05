package com.manage.courses.course;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonSnakeCase
public class CreateCourseRequest {
    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private int maxScore;

}

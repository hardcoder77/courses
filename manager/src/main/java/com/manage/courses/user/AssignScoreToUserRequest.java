package com.manage.courses.user;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

@Data
@JsonSnakeCase
public class AssignScoreToUserRequest {
    private String courseName;
    private String userEmail;
    private float score;
}

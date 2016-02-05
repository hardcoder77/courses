package com.manage.courses.user;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

@Data
@JsonSnakeCase
public class AssignScoreToUserResponse {
    public static final String SUCCESSFULLY_UPDATED = "SUCCESSFULLY_UPDATED";
    public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String COURSE_NOT_FOUND = "COURSE_NOT_FOUND";
    public static final String USER_COURSE_INVITE_NOT_FOUND = "COURSE_INVITE_NOT_FOUND";
    public static final String SCORE_ALREADY_ASSIGNED = "SCORE_ALREADY_ASSIGNED";
    private String userEmail;
    private String courseName;
    private String message;
}

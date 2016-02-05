package com.manage.courses.user;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

@Data
@JsonSnakeCase
public class InviteUserToCourseResponse {
    public static final String SUCCESSFULLY_INVITED = "SUCCESSFULLY_INVITED";
    public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String COURSE_NOT_FOUND = "COURSE_NOT_FOUND";
    public static final String USER_ALREADY_INVITED = "USER_ALREADY_INVITED";
    private String userEmail;
    private String courseName;
    private String message;
}

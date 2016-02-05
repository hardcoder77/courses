package com.manage.courses.user;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonSnakeCase
public class InviteUserToCourseRequest {
    @NotNull
    private String userEmail;

    @NotNull
    private String courseName;
}

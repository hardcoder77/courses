package com.manage.courses.user;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonSnakeCase
public class CreateUserRequest {
    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String phone;
}

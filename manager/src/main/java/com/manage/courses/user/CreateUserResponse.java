package com.manage.courses.user;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

@Data
@JsonSnakeCase
public class CreateUserResponse {
    private String userId;
    private String name;
    private String email;
    private String phone;
}

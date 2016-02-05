package com.manage.courses.user;

import com.google.common.collect.Lists;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

import java.util.List;

@Data
@JsonSnakeCase
public class ListUsersResponse {
    private List<CreateUserResponse> userList = Lists.newArrayList();
}

package com.manage.courses.course;

import com.manage.courses.user.InviteUserToCourseRequest;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

import java.util.List;

@Data
@JsonSnakeCase
public class InviteUsersToCourseBulkRequest {
    private List<InviteUserToCourseRequest> requests;
}

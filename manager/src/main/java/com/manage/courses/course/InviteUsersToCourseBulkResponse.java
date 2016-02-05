package com.manage.courses.course;

import com.google.common.collect.Lists;
import com.manage.courses.user.InviteUserToCourseResponse;
import lombok.Data;

import java.util.List;

@Data
public class InviteUsersToCourseBulkResponse {
    private List<InviteUserToCourseResponse> responses = Lists.newArrayList();
}

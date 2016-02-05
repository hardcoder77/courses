package com.manage.courses.internal.course;

import com.google.inject.Inject;
import com.manage.courses.course.InviteUsersToCourseBulkRequest;
import com.manage.courses.course.InviteUsersToCourseBulkResponse;
import com.manage.courses.user.InviteUserToCourseRequest;
import com.manage.courses.user.InviteUserToCourseResponse;

import javax.ws.rs.WebApplicationException;

public class InviteUsersToCourseBulkAction {
    private final InviteUserToCourseAction inviteUserToCourseAction;
    private InviteUsersToCourseBulkRequest inviteUsersToCourseBulkRequest;

    @Inject
    public InviteUsersToCourseBulkAction(InviteUserToCourseAction inviteUserToCourseAction) {
        this.inviteUserToCourseAction = inviteUserToCourseAction;
    }

    public InviteUsersToCourseBulkResponse invoke() {
        InviteUsersToCourseBulkResponse inviteUsersToCourseBulkResponse = new InviteUsersToCourseBulkResponse();
        for (InviteUserToCourseRequest inviteUserToCourseRequest : inviteUsersToCourseBulkRequest.getRequests()) {
            try {
                inviteUsersToCourseBulkResponse.getResponses().add(inviteUserToCourseAction.withUserEmail(inviteUserToCourseRequest.getUserEmail()).withCourseName(inviteUserToCourseRequest.getCourseName()).invoke());

            } catch (WebApplicationException e) {
                inviteUsersToCourseBulkResponse.getResponses().add((InviteUserToCourseResponse) e.getResponse().getEntity());
            }

        }
        return inviteUsersToCourseBulkResponse;
    }

    public InviteUsersToCourseBulkAction withInviteUsersToCourseBulkRequest(InviteUsersToCourseBulkRequest inviteUsersToCourseBulkRequest) {
        this.inviteUsersToCourseBulkRequest = inviteUsersToCourseBulkRequest;
        return this;
    }
}

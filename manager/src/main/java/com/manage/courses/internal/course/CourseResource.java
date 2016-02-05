package com.manage.courses.internal.course;

import com.google.inject.Inject;
import com.manage.courses.course.*;
import com.manage.courses.user.InviteUserToCourseRequest;
import com.manage.courses.user.InviteUserToCourseResponse;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/courses")
@Slf4j
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

    private final CreateCourseAction createCourseAction;
    private final InviteUserToCourseAction inviteUserToCourseAction;
    private final InviteUsersToCourseBulkAction inviteUsersToCourseBulkAction;

    @Inject
    public CourseResource(CreateCourseAction createCourseAction, InviteUserToCourseAction inviteUserToCourseAction, InviteUsersToCourseBulkAction inviteUsersToCourseBulkAction) {
        this.createCourseAction = createCourseAction;
        this.inviteUserToCourseAction = inviteUserToCourseAction;
        this.inviteUsersToCourseBulkAction = inviteUsersToCourseBulkAction;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CreateCourseResponse createCourse(@Valid CreateCourseRequest createCourseRequest) {
        return createCourseAction.withCreateCourseRequest(createCourseRequest).invoke();
    }

    @POST
    @Path("/invitations/users")
    @Produces(MediaType.APPLICATION_JSON)
    public InviteUserToCourseResponse inviteUserToCourse(@Valid InviteUserToCourseRequest inviteUserToCourseRequest) {
        return inviteUserToCourseAction.withUserEmail(inviteUserToCourseRequest.getUserEmail()).withCourseName(inviteUserToCourseRequest.getCourseName()).invoke();

    }

    @POST
    @Path("/invitations/users/bulk")
    @Produces(MediaType.APPLICATION_JSON)
    public InviteUsersToCourseBulkResponse inviteUsersToCourseBulk(@Valid InviteUsersToCourseBulkRequest inviteUsersToCourseBulkRequest) {
        return inviteUsersToCourseBulkAction.withInviteUsersToCourseBulkRequest(inviteUsersToCourseBulkRequest).invoke();

    }


}

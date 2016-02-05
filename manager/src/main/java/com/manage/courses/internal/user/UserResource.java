package com.manage.courses.internal.user;

import com.google.inject.Inject;
import com.manage.courses.internal.course.InviteUserToCourseAction;
import com.manage.courses.user.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Slf4j
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final CreateUserAction createUserAction;
    private final AssignScoreToUserAction assignScoreToUserAction;
    private final InviteUserToCourseAction inviteUserToCourseAction;
    private final SearchUsersInvitedForCourseAction searchUsersInvitedForCourseAction;
    private final GetTop10UsersByTotalMarksAction getTop10UsersByTotalMarksAction;

    @Inject
    public UserResource(CreateUserAction createUserAction, AssignScoreToUserAction assignScoreToUserAction, InviteUserToCourseAction inviteUserToCourseAction, SearchUsersInvitedForCourseAction searchUsersInvitedForCourseAction, GetTop10UsersByTotalMarksAction getTop10UsersByTotalMarksAction) {
        this.createUserAction = createUserAction;
        this.assignScoreToUserAction = assignScoreToUserAction;
        this.inviteUserToCourseAction = inviteUserToCourseAction;
        this.searchUsersInvitedForCourseAction = searchUsersInvitedForCourseAction;
        this.getTop10UsersByTotalMarksAction = getTop10UsersByTotalMarksAction;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CreateUserResponse createUser(@Valid CreateUserRequest createUserRequest) {
        return createUserAction.withCreateUserRequest(createUserRequest).invoke();
    }

    @Path("/courses/score")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AssignScoreToUserResponse assignScoreToUser(@Valid AssignScoreToUserRequest assignScoreToUserRequest) {
        return assignScoreToUserAction.withCourseName(assignScoreToUserRequest.getCourseName()).withUserEmail(assignScoreToUserRequest.getUserEmail()).withScore(assignScoreToUserRequest.getScore()).invoke();
    }

    @GET
    @Path("/invitations/search")
    @Produces(MediaType.APPLICATION_JSON)
    public ListUsersResponse searchUsersInvitedForCourse(@QueryParam("course_name") String courseName) {
        return searchUsersInvitedForCourseAction.withCourseName(courseName).invoke();

    }

    @GET
    @Path("/search/top10")
    @Produces(MediaType.APPLICATION_JSON)
    public ListUsersResponse getTop10UsersByTotalMarks() {
        return getTop10UsersByTotalMarksAction.invoke();
    }

}

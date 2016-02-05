package com.manage.courses.internal.user;

import com.google.inject.Inject;
import com.manage.courses.course.ICourseRepository;
import com.manage.courses.internal.course.model.Course;
import com.manage.courses.internal.user.model.User;
import com.manage.courses.user.AssignScoreToUserResponse;
import com.manage.courses.user.IUserRepository;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class AssignScoreToUserAction {

    private final IUserRepository userRepository;
    private final ICourseRepository courseRepository;
    private String userEmail;
    private String courseName;
    private float score;

    @Inject
    public AssignScoreToUserAction(IUserRepository userRepository, ICourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public AssignScoreToUserAction withUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public AssignScoreToUserAction withCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public AssignScoreToUserAction withScore(float score) {
        this.score = score;
        return this;
    }

    public AssignScoreToUserResponse invoke() {
        Optional<User> userOptional = userRepository.getUserByEmailId(userEmail);
        Optional<Course> courseOptional = courseRepository.getCourseByName(courseName);
        checkIfUserIsPresent(userOptional);
        checkIfCourseIsPresent(courseOptional);
        checkIfUserIsInvited(userOptional, courseOptional);
        checkIfScoreIsAlreadyAssigned(userOptional, courseOptional);
        userRepository.assignScoreToUser(userOptional.get().getUserId(), courseOptional.get().getCourseId(), score);
        return constructAssignScoreToUserResponse(AssignScoreToUserResponse.SUCCESSFULLY_UPDATED);

    }

    private AssignScoreToUserResponse constructAssignScoreToUserResponse(String status) {
        AssignScoreToUserResponse assignScoreToUserResponse = new AssignScoreToUserResponse();
        assignScoreToUserResponse.setCourseName(courseName);
        assignScoreToUserResponse.setUserEmail(userEmail);
        assignScoreToUserResponse.setMessage(status);
        return assignScoreToUserResponse;
    }

    private void checkIfScoreIsAlreadyAssigned(Optional<User> userOptional, Optional<Course> courseOptional) {
        Optional<Float> score = userRepository.getScoreForUserInCourse(userOptional.get().getUserId(), courseOptional.get().getCourseId());
        if (score.isPresent()) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity(constructAssignScoreToUserResponse(AssignScoreToUserResponse.SCORE_ALREADY_ASSIGNED)).build());
        }
    }

    private void checkIfUserIsInvited(Optional<User> userOptional, Optional<Course> courseOptional) {
        boolean isUserInvited = courseRepository.hasUserBeenInvited(userOptional.get().getUserId(), courseOptional.get().getCourseId());
        if (!isUserInvited) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(constructAssignScoreToUserResponse(AssignScoreToUserResponse.USER_COURSE_INVITE_NOT_FOUND)).build());
        }
    }

    private void checkIfCourseIsPresent(Optional<Course> courseOptional) {
        if (!courseOptional.isPresent()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(constructAssignScoreToUserResponse(AssignScoreToUserResponse.COURSE_NOT_FOUND)).build());
        }
    }

    private void checkIfUserIsPresent(Optional<User> userOptional) {
        if (!userOptional.isPresent()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(constructAssignScoreToUserResponse(AssignScoreToUserResponse.USER_NOT_FOUND)).build());
        }
    }


}

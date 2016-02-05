package com.manage.courses.internal.course;

import com.google.inject.Inject;
import com.manage.courses.course.ICourseRepository;
import com.manage.courses.internal.course.model.Course;
import com.manage.courses.internal.user.model.User;
import com.manage.courses.user.IUserRepository;
import com.manage.courses.user.InviteUserToCourseResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class InviteUserToCourseAction {
    private final IUserRepository userRepository;
    private final ICourseRepository courseRepository;
    private String userEmail;
    private String courseName;

    @Inject
    public InviteUserToCourseAction(IUserRepository userRepository, ICourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public InviteUserToCourseAction withUserEmail(String email) {
        this.userEmail = email;
        return this;
    }

    public InviteUserToCourseAction withCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public InviteUserToCourseResponse invoke() {
        Optional<User> userOptional = userRepository.getUserByEmailId(userEmail);
        Optional<Course> courseOptional = courseRepository.getCourseByName(courseName);
        checkIfUserIsPresent(userOptional);
        checkIfCourseIsPresent(courseOptional);
        checkIfUserIsAlreadyInvitedForTheCourse(userOptional, courseOptional);
        courseRepository.inviteUserToCourse(userOptional.get().getUserId(), courseOptional.get().getCourseId());
        return constructInviteUserToCourseResponse(InviteUserToCourseResponse.SUCCESSFULLY_INVITED);
    }

    private void checkIfUserIsAlreadyInvitedForTheCourse(Optional<User> userOptional, Optional<Course> courseOptional) {
        if (courseRepository.hasUserBeenInvited(userOptional.get().getUserId(), courseOptional.get().getCourseId())) {
            Response response = Response.status(Response.Status.CONFLICT).entity(constructInviteUserToCourseResponse(InviteUserToCourseResponse.USER_ALREADY_INVITED)).build();
            throw new WebApplicationException(response);
        }
    }

    private void checkIfCourseIsPresent(Optional<Course> courseOptional) {
        if (!courseOptional.isPresent()) {
            Response response = Response.status(Response.Status.NOT_FOUND).entity(constructInviteUserToCourseResponse(InviteUserToCourseResponse.COURSE_NOT_FOUND)).build();
            throw new WebApplicationException(response);
        }
    }

    private void checkIfUserIsPresent(Optional<User> userOptional) {
        if (!userOptional.isPresent()) {
            Response response = Response.status(Response.Status.NOT_FOUND).entity(constructInviteUserToCourseResponse(InviteUserToCourseResponse.USER_NOT_FOUND)).build();
            throw new WebApplicationException(response);
        }
    }

    private InviteUserToCourseResponse constructInviteUserToCourseResponse(String status) {
        InviteUserToCourseResponse inviteUserToCourseResponse = new InviteUserToCourseResponse();
        inviteUserToCourseResponse.setUserEmail(userEmail);
        inviteUserToCourseResponse.setCourseName(courseName);
        inviteUserToCourseResponse.setMessage(status);
        return inviteUserToCourseResponse;
    }
}

package com.manage.courses.internal.user;

import com.google.inject.Inject;
import com.manage.courses.course.ICourseRepository;
import com.manage.courses.internal.course.model.Course;
import com.manage.courses.internal.user.model.User;
import com.manage.courses.user.CreateUserResponse;
import com.manage.courses.user.IUserRepository;
import com.manage.courses.user.ListUsersResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Set;

public class SearchUsersInvitedForCourseAction {
    private String courseName;
    private final IUserRepository userRepository;
    private final ICourseRepository courseRepository;

    @Inject
    public SearchUsersInvitedForCourseAction(IUserRepository userRepository, ICourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public SearchUsersInvitedForCourseAction withCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public ListUsersResponse invoke() {
        Optional<Course> courseOptional = courseRepository.getCourseByName(courseName);
        checkIfCourseIsPresent(courseOptional);
        ListUsersResponse listUsersResponse = new ListUsersResponse();
        Set<String> userIds =  userRepository.getUsersInvitedForCourse(courseOptional.get().getCourseId());
        for (String userId : userIds) {
            User user = userRepository.getUserByUserId(userId).get();
            CreateUserResponse createUserResponse = constructCreateUserResponse(user);
            listUsersResponse.getUserList().add(createUserResponse);
        }
        return listUsersResponse;
    }

    private CreateUserResponse constructCreateUserResponse(User user) {
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setUserId(user.getUserId());
        createUserResponse.setEmail(user.getEmail());
        createUserResponse.setPhone(user.getPhone());
        createUserResponse.setName(user.getName());
        return createUserResponse;
    }

    private void checkIfCourseIsPresent(Optional<Course> courseOptional) {
        if (!courseOptional.isPresent()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity("Course not found").build());
        }
    }
}

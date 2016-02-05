package com.manage.courses.internal.course;


import com.google.inject.Inject;
import com.manage.courses.course.CreateCourseRequest;
import com.manage.courses.course.CreateCourseResponse;
import com.manage.courses.course.ICourseRepository;
import com.manage.courses.internal.course.model.Course;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class CreateCourseAction {
    private final ICourseRepository courseRepository;
    private CreateCourseRequest createCourseRequest;

    @Inject
    public CreateCourseAction(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CreateCourseResponse invoke() {
        Course course = createCourseModelFromCreateCourseRequest();
        if (courseRepository.getCourseByName(course.getName()).isPresent()) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity("Course already exists").build());
        }
        courseRepository.createCourse(course);
        return constructCreateCourseResponse(course);
    }

    private CreateCourseResponse constructCreateCourseResponse(Course course) {
        CreateCourseResponse createCourseResponse = new CreateCourseResponse();
        createCourseResponse.setName(course.getName());
        createCourseResponse.setMaxScore(course.getMaxScore());
        createCourseResponse.setDescription(course.getDescription());
        createCourseResponse.setCourseId(course.getCourseId());
        return createCourseResponse;
    }

    private Course createCourseModelFromCreateCourseRequest() {
        Course course = new Course();
        course.setName(createCourseRequest.getName());
        course.setDescription(createCourseRequest.getDescription());
        course.setMaxScore(createCourseRequest.getMaxScore());
        return course;
    }

    public CreateCourseAction withCreateCourseRequest(CreateCourseRequest createCourseRequest) {
        this.createCourseRequest = createCourseRequest;
        return this;
    }
}

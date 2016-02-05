package com.manage.courses.course;

import com.manage.courses.internal.course.model.Course;

import java.util.Optional;

public interface ICourseRepository {
    Optional<Course> getCourseByName(String name);

    void createCourse(Course course);

    void inviteUserToCourse(String userEmail, String courseId);

    boolean hasUserBeenInvited(String userId, String courseId);
}

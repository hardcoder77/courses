package com.manage.courses.internal.course;

import com.google.inject.Inject;
import com.manage.courses.course.ICourseRepository;
import com.manage.courses.internal.course.model.Course;
import com.manage.courses.internal.datastore.IDataStore;

import java.util.Optional;

public class CourseRepository implements ICourseRepository {

    private final IDataStore dataStore;

    @Inject
    public CourseRepository(IDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<Course> getCourseByName(String name) {
        return dataStore.getCourseByName(name);
    }

    @Override
    public void createCourse(Course course) {
        dataStore.addCourse(course);
    }

    @Override
    public void inviteUserToCourse(String userEmail, String courseId) {
        dataStore.inviteUserToCourse(userEmail, courseId);
    }

    @Override
    public boolean hasUserBeenInvited(String userId, String courseId) {
        return dataStore.hasUserBeenInvitedToCourse(userId, courseId);
    }
}

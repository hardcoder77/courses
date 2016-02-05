package com.manage.courses.internal.datastore;

import com.manage.courses.internal.course.model.Course;
import com.manage.courses.internal.user.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IDataStore {
    void addUser(User user);

    Optional<User> getUserByEmailId(String email);

    void addCourse(Course course);

    Optional<Course> getCourseByName(String name);

    void inviteUserToCourse(String userId, String courseId);

    boolean hasUserBeenInvitedToCourse(String userId, String courseId);

    Optional<Float> getScoreForUserInCourse(String userId, String courseId);

    void assignScoreToUser(String userId, String courseId, float score);

    Set<String> getUsersInvitedForCourse(String courseId);

    Optional<User> getUserByUserId(String userId);

    List<String> getTop10UsersByTotalMarks();
}

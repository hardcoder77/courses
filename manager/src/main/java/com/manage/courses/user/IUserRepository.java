package com.manage.courses.user;


import com.manage.courses.internal.user.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IUserRepository {
    List<User> getAllUsers();
    void createUser(User user);
    Optional<User> getUserByEmailId(String email);
    void inviteUserToCourse(String userId, String courseId);

    void assignScoreToUser(String userId, String courseId, float score);

    Optional<Float> getScoreForUserInCourse(String userId, String courseId);

    Set<String> getUsersInvitedForCourse(String courseId);

    Optional<User> getUserByUserId(String userId);

    List<String> getTop10UsersByTotalMarks();
}

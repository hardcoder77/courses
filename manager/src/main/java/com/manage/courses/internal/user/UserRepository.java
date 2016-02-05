package com.manage.courses.internal.user;

import com.google.inject.Inject;
import com.manage.courses.internal.datastore.IDataStore;
import com.manage.courses.internal.user.model.User;
import com.manage.courses.user.IUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserRepository implements IUserRepository {

    private final IDataStore dataStore;

    @Inject
    public UserRepository(IDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void createUser(User user) {
        dataStore.addUser(user);
    }

    @Override
    public Optional<User> getUserByEmailId(String email) {
        return dataStore.getUserByEmailId(email);
    }

    @Override
    public void inviteUserToCourse(String userEmail, String courseId) {
        dataStore.inviteUserToCourse(userEmail, courseId);
    }

    @Override
    public void assignScoreToUser(String userId, String courseId, float score) {
        dataStore.assignScoreToUser(userId, courseId, score);
    }

    @Override
    public Optional<Float> getScoreForUserInCourse(String userId, String courseId) {
        return dataStore.getScoreForUserInCourse(userId, courseId);
    }

    @Override
    public Set<String> getUsersInvitedForCourse(String courseId) {
        return dataStore.getUsersInvitedForCourse(courseId);
    }

    @Override
    public Optional<User> getUserByUserId(String userId) {
        return dataStore.getUserByUserId(userId);
    }

    @Override
    public List<String> getTop10UsersByTotalMarks() {
        return dataStore.getTop10UsersByTotalMarks();
    }


}

package com.manage.courses.internal.datastore;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.manage.courses.internal.course.model.Course;
import com.manage.courses.internal.user.model.User;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

public class DataStore implements IDataStore {

    private Map<String, User> users = Maps.newConcurrentMap();
    private Map<String, String> userIdEmailMap = Maps.newConcurrentMap();
    private Map<String, Course> courses = Maps.newConcurrentMap();
    private Map<String, Set<String>> courseInvitations = Maps.newConcurrentMap();
    private Map<String, Map<String, Float>> userScores = Maps.newConcurrentMap();
    private PriorityBlockingQueue<AbstractMap.SimpleEntry<String, Float>> top10UsersByTotalMarks;

    public DataStore() {
        top10UsersByTotalMarks = new PriorityBlockingQueue<>(10, new TotalMarksComparator());
    }

    @Override
    public void addUser(User user) {
        users.put(user.getEmail(), user);
        userScores.put(user.getUserId(), Maps.newConcurrentMap());
        userIdEmailMap.put(user.getUserId(), user.getEmail());
    }

    public Optional<User> getUserByEmailId(String email) {
        if (users.containsKey(email)) {
            return Optional.of(users.get(email));
        }
        return Optional.empty();
    }

    @Override
    public void addCourse(Course course) {
        courses.put(course.getName(), course);
        courseInvitations.put(course.getCourseId(), Sets.newConcurrentHashSet());
    }

    @Override
    public Optional<Course> getCourseByName(String name) {
        if (courses.containsKey(name)) {
            return Optional.of(courses.get(name));
        }
        return Optional.empty();

    }

    @Override
    public void inviteUserToCourse(String userId, String courseId) {
        courseInvitations.get(courseId).add(userId);
    }

    @Override
    public boolean hasUserBeenInvitedToCourse(String userId, String courseId) {
        return courseInvitations.get(courseId).contains(userId);
    }

    @Override
    public Optional<Float> getScoreForUserInCourse(String userId, String courseId) {
        if (userScores.get(userId).containsKey(courseId)) {
            return Optional.of(userScores.get(userId).get(courseId));
        }
        return Optional.empty();
    }

    @Override
    public void assignScoreToUser(String userId, String courseId, float score) {
        userScores.get(userId).put(courseId, score);
        updateTotalMarksForUser(userId, score);
        updateTop10UsersByTotalMarks(userId, courseId, score);
    }

    private void updateTotalMarksForUser(String userId, float score) {
        users.get(userIdEmailMap.get(userId)).setTotalMarks(users.get(userIdEmailMap.get(userId)).getTotalMarks() + score);
    }

    private void updateTop10UsersByTotalMarks(String userId, String courseId, float score) {
        if (top10UsersByTotalMarks.contains(new AbstractMap.SimpleEntry<>(userId, users.get(userIdEmailMap.get(userId)).getTotalMarks() - score))) {
            top10UsersByTotalMarks.remove(new AbstractMap.SimpleEntry<>(userId, users.get(userIdEmailMap.get(userId)).getTotalMarks() - score));
        }
        top10UsersByTotalMarks.add(new AbstractMap.SimpleEntry<>(userId, users.get(userIdEmailMap.get(userId)).getTotalMarks()));
        if (top10UsersByTotalMarks.size() > 10) {
            top10UsersByTotalMarks.remove();
        }
    }

    @Override
    public Set<String> getUsersInvitedForCourse(String courseId) {
        return courseInvitations.get(courseId);
    }

    @Override
    public Optional<User> getUserByUserId(String userId) {
        if (users.containsKey(userIdEmailMap.get(userId))) {
            return Optional.of(users.get(userIdEmailMap.get(userId)));
        }
        return Optional.empty();
    }

    @Override
    public List<String> getTop10UsersByTotalMarks() {
        List<String> userIds = Lists.newArrayList();
        for (AbstractMap.SimpleEntry<String, Float> top10UsersByTotalMark : top10UsersByTotalMarks) {
            userIds.add(top10UsersByTotalMark.getKey());
        }
        return userIds;
    }

}

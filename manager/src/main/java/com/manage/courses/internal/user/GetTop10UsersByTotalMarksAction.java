package com.manage.courses.internal.user;

import com.google.inject.Inject;
import com.manage.courses.internal.user.model.User;
import com.manage.courses.user.CreateUserResponse;
import com.manage.courses.user.IUserRepository;
import com.manage.courses.user.ListUsersResponse;

import java.util.List;
import java.util.Optional;

public class GetTop10UsersByTotalMarksAction {
    private final IUserRepository userRepository;

    @Inject
    public GetTop10UsersByTotalMarksAction(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ListUsersResponse invoke() {
        List<String> userIds = userRepository.getTop10UsersByTotalMarks();
        ListUsersResponse listUsersResponse = new ListUsersResponse();
        for (String userId : userIds) {
            Optional<User> userOptional = userRepository.getUserByUserId(userId);
            CreateUserResponse response = constructCreateUserResponse(userOptional);
            listUsersResponse.getUserList().add(response);
        }
        return listUsersResponse;
    }

    private CreateUserResponse constructCreateUserResponse(Optional<User> userOptional) {
        CreateUserResponse response = new CreateUserResponse();
        response.setName(userOptional.get().getName());
        response.setUserId(userOptional.get().getUserId());
        response.setEmail(userOptional.get().getEmail());
        response.setPhone(userOptional.get().getPhone());
        return response;
    }
}

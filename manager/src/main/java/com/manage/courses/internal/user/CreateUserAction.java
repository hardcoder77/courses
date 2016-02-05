package com.manage.courses.internal.user;


import com.google.inject.Inject;
import com.manage.courses.internal.user.model.User;
import com.manage.courses.user.CreateUserRequest;
import com.manage.courses.user.CreateUserResponse;
import com.manage.courses.user.IUserRepository;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class CreateUserAction {
    private final IUserRepository userRepository;
    private CreateUserRequest createUserRequest;

    @Inject
    public CreateUserAction(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserResponse invoke() {
        User user = createUserModelFromCreateUserRequest();
        if (userRepository.getUserByEmailId(user.getEmail()).isPresent()) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity("User already exists").build());
        }
        userRepository.createUser(user);
        return constructCreateUserResponse(user);
    }

    private CreateUserResponse constructCreateUserResponse(User user) {
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setName(user.getName());
        createUserResponse.setPhone(user.getPhone());
        createUserResponse.setEmail(user.getEmail());
        createUserResponse.setUserId(user.getUserId());
        return createUserResponse;
    }

    private User createUserModelFromCreateUserRequest() {
        User user = new User();
        user.setName(createUserRequest.getName());
        user.setEmail(createUserRequest.getEmail());
        user.setPhone(createUserRequest.getPhone());
        return user;
    }

    public CreateUserAction withCreateUserRequest(CreateUserRequest createUserRequest) {
        this.createUserRequest = createUserRequest;
        return this;
    }
}

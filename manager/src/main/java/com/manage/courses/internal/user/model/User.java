package com.manage.courses.internal.user.model;

import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class User {
    public User() {
        this.userId = UUID.randomUUID().toString();
    }
    private String userId;
    private String name;
    private String email;
    private String phone;
    private float totalMarks = 0;

    @Override
    public boolean equals(Object object) {
        return Objects.equals(((User) object).getEmail(), this.getEmail());
    }

    @Override
    public int hashCode() {
        return this.getEmail().hashCode();
    }
}

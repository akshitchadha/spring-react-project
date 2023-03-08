package com.course.api.gateway.service;

import com.course.api.gateway.model.Role;
import com.course.api.gateway.model.User;

import java.util.Optional;

public interface UserService {
    User SaveUser(User user);

    Optional<User> findbyUsername(String username);

    void changeRole(String username, Role role);
}

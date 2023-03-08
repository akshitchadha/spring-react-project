package com.course.api.gateway.service;

import com.course.api.gateway.model.User;

public interface AuthenticationService {
    User signInandReturnJwt(User signInRequest);
}

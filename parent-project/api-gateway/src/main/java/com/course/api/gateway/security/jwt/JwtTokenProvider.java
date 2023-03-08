package com.course.api.gateway.security.jwt;

import com.course.api.gateway.security.CustomUserDetails;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public abstract class JwtTokenProvider {
    public abstract String generateToken(CustomUserDetails customUserDetails);

    public abstract Authentication getauthentication  (HttpServletRequest httpServletRequest);

    public abstract Boolean isTokenValid(HttpServletRequest httpServletRequest);
}

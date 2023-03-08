package com.course.api.gateway.service;

import com.course.api.gateway.model.User;
import com.course.api.gateway.security.CustomUserDetails;
import com.course.api.gateway.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

   private AuthenticationManager authenticationManager;

   private JwtTokenProvider jwtTokenProvider;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    public User signInandReturnJwt(User signInRequest)
    {
        Authentication authentication=authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsername(),signInRequest.getPassword()));


        CustomUserDetails customUserDetails= (CustomUserDetails) authentication.getPrincipal();
        String jwt=jwtTokenProvider.generateToken(customUserDetails);


        User signInuser= customUserDetails.getUser();
        signInuser.setToken(jwt);

        return  signInuser;



    }



}

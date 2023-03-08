package com.course.api.gateway.controller;

import com.course.api.gateway.model.Role;
import com.course.api.gateway.security.CustomUserDetails;
import com.course.api.gateway.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user") //pre path
public class UserController {


    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("change/{role}")
    public ResponseEntity<?> getUserService(@AuthenticationPrincipal CustomUserDetails
                                              customUserDetails, @PathVariable Role role) {
       userService.changeRole(customUserDetails.getUsername() ,role);

        return ResponseEntity.ok(true);
    }



}

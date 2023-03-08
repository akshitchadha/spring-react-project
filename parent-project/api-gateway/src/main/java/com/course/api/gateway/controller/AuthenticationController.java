package com.course.api.gateway.controller;

import com.course.api.gateway.model.User;
import com.course.api.gateway.service.AuthenticationService;
import com.course.api.gateway.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    private UserService userService;


    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("signup")
    public ResponseEntity<?> signIn(@RequestBody User user)
    {
        if (userService.findbyUsername(user.getUsername()).isPresent())
        {
              return new ResponseEntity<>(HttpStatus.CONFLICT);
        }


        return new ResponseEntity<>(userService.SaveUser(user),HttpStatus.CREATED);
    }



    @PostMapping("signin")
    public ResponseEntity<?> signin(@RequestBody User user)
    {

        return  new ResponseEntity<>(authenticationService.signInandReturnJwt(user),HttpStatus.OK);
    }

}

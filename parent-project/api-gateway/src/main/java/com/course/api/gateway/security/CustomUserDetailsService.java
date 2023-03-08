package com.course.api.gateway.security;

import com.course.api.gateway.model.User;
import com.course.api.gateway.service.UserService;
import com.course.api.gateway.util.SecurityUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user=userService.findbyUsername(username).orElseThrow(()->new
               UsernameNotFoundException("user not with username : "+username));
        Set<GrantedAuthority> authorities=Set.of(SecurityUtil.convertToAuthority(user.getRole().name()));
        CustomUserDetails  customUserDetails=new
                 CustomUserDetails(user.getId(),user.getUsername(),user.getPassword(),user,authorities);
               return customUserDetails;
    }
}

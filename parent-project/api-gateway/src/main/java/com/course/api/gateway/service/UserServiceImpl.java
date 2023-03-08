package com.course.api.gateway.service;

import com.course.api.gateway.model.Role;
import com.course.api.gateway.model.User;
import com.course.api.gateway.respository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User SaveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreateTime(LocalDateTime.now());
        return userRepository.save(user);
    }


    @Override
    public Optional<User> findbyUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    @Transactional
    public void changeRole(String username, Role role) {
        userRepository.updateUserRole(username, role);
    }
}

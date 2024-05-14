package com.codewithproject.springsecurity.services;

import com.codewithproject.springsecurity.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDetailsService userDetailService();

    List<User> seederUser();

    Optional<User> getUserDetail(String email);
}

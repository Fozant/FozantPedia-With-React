package com.fauzan.springboot.springBootFauzan.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fauzan.springboot.springBootFauzan.model.Role;
import com.fauzan.springboot.springBootFauzan.model.User;
import com.fauzan.springboot.springBootFauzan.service.UserService;
import com.fauzan.springboot.springBootFauzan.web.dto.UserRegistration;

@RestController
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;
    private final BCryptPasswordEncoder pwencoder;

    @Autowired
    public UserRegistrationController(UserService userService, BCryptPasswordEncoder pwencoder) {
        this.userService = userService;
        this.pwencoder = pwencoder;
    }

    @PostMapping
    public String registerUserAccount(@RequestBody UserRegistration registration) {
        Role userRole = new Role("ROLE_USER");

        String encodedPassword = pwencoder.encode(registration.getPassword());

        User user = new User(registration.getFirstName(), registration.getLastName(), registration.getEmail(),
                encodedPassword, Collections.singletonList(userRole));

        userService.save(user);
        return "Registration successful";
    }
}

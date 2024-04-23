package com.chatop.api.controller;

import com.chatop.api.model.User;
import com.chatop.api.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public User createToken(User user) throws Exception {
        return userService.createUser(user);
    }
}

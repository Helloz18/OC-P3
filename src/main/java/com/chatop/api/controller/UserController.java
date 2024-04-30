package com.chatop.api.controller;

import com.chatop.api.dto.UserDTO;
import com.chatop.api.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.chatop.api.model.User;

@RestController
@Tag(name = "User controller", description = "One endpoint to get a user by its id.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) throws Exception {
        return userService.getUserById(id);
    }
}

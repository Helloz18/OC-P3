package com.chatop.api.controller;

import com.chatop.api.security.JwtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Login controller", description = "Endpoint used to log in the application")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @Autowired
    private JwtService jwtService;


    @PostMapping("/login")
    public String getToken(Authentication authentication) {
        String token = jwtService.generateToken(authentication);
        return token;
    }
}

package com.chatop.api.controller;

import com.chatop.api.model.LoginRequest;
import com.chatop.api.model.Token;
import com.chatop.api.security.JwtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Login controller", description = "Endpoint used to log in the application")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/login")
    public ResponseEntity<?> getToken(@RequestBody LoginRequest loginRequest) {

        // Création d'un jeton d'authentification avec les informations de connexion
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        // Si l'authentification réussit, générez le jeton JWT
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authentication);
            Token t = new Token();
            t.setToken(token);
            return ResponseEntity.ok(t);
        } else {
            System.out.println("ici");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erreur");
        }
    }

}

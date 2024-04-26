package com.chatop.api.controller;

import com.chatop.api.dto.ModelConverter;
import com.chatop.api.dto.UserDTO;
import com.chatop.api.model.LoginRequest;
import com.chatop.api.model.Token;
import com.chatop.api.model.User;
import com.chatop.api.security.JwtService;
import com.chatop.api.service.UserService;
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

import java.time.Instant;

@RestController
@Tag(name = "Auth controller", description = "Endpoint used to log in the application and to register a new user.")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if(loginRequest.getLogin().isEmpty() || loginRequest.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erreur");
        } else {
            Token t = generateToken(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(t);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody final UserDTO userDTO) throws Exception {
        System.out.println(userDTO.getEmail());
        if(userDTO.getName() == null || userDTO.getEmail() == null || userDTO.getPassword() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            User user = ModelConverter.toUser(userDTO);
            user.setCreatedAt(Instant.now().toString());
            userService.createUser(user);
        }
        Token t = generateToken(userDTO.getEmail(), userDTO.getPassword());
        return ResponseEntity.ok(t);
    }

    private Token generateToken(String email, String password) {
        Token t = new Token();
        // Création d'un jeton d'authentification avec les informations de connexion
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        // Si l'authentification réussit, générez le jeton JWT
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authentication);
            t.setToken(token);
            return t;
        }
        return t;
    }

}

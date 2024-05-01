package com.chatop.api.controller;

import com.chatop.api.dto.UserDTO;
import com.chatop.api.model.LoginRequest;
import com.chatop.api.model.ResponseMessage;
import com.chatop.api.model.Token;
import com.chatop.api.model.User;
import com.chatop.api.security.JwtService;
import com.chatop.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Auth controller", description = "Controller used to log in the application and to register a new user.")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @Operation(summary = "Log in the application.",
            description = "Give a valid email and password, then a token will be generated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "a JWT token.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Token.class))),
            @ApiResponse(responseCode = "401", description = "error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessage.class)))
    })
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if(userService.getUserByEmail(loginRequest.getEmail()) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("error"));
        } else {
            Token t = createToken(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(t);
        }
    }


    @PostMapping("/register")
    @Operation(summary = "Register in the application. The user is saved in the database.",
            description = "Give a new email, name and password, then a token will be generated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "a JWT token.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Token.class))),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)))
    })
    public ResponseEntity<?> registerUser(@RequestBody final UserDTO userDTO) throws Exception {
        if(userDTO.getName() == null || userDTO.getEmail() == null || userDTO.getPassword() == null
        || userDTO.getName().isEmpty() || userDTO.getEmail().isEmpty() || userDTO.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            userService.createUser(userDTO);
        }
        Token t = createToken(userDTO.getEmail(), userDTO.getPassword());
        return ResponseEntity.ok(t);
    }

    /**
     * Method to create the token for login and registration.
     * @param email
     * @param password
     * @return
     */
    private Token createToken(String email, String password) {
        Token t = new Token();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authentication);
            t.setToken(token);
            return t;
        }
        return t;
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/me")
    @Operation(summary = "Get information of the connected user.",
            description = "The user must be connected as we need the token in the header.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "a User.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "401", description = "",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)))
    })
    public ResponseEntity<?> getConnectedUser(
            @Parameter(description = "Bearer token", example="Bearer eyJhbGciOJIUzI1NiJ9...")
            @RequestHeader("Authorization") String bearer) {
        if(bearer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        } else {
            //The bearer token from the header is given with "bearer " before the actual token.
            //we remove this part to just give the token to the service.
            String username = jwtService.extractUsername(bearer.substring(7));
            User user = userService.getUserByEmail(username);
            return ResponseEntity.ok(user);
        }
    }

}

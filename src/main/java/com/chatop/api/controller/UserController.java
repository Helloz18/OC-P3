package com.chatop.api.controller;

import com.chatop.api.dto.UserDTO;
import com.chatop.api.model.ResponseMessage;
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
    @Operation(summary = "Get the user by its id.",
            description = "Returns a user." )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "401",
                    content = @Content(mediaType = "application/json"))
    })
    public User getUserById(
            @Parameter(description = "id of the user", example="5")
            @PathVariable int id) throws Exception {
        return userService.getUserById(id);
    }
}

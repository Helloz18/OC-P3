package com.chatop.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class RentalController {

    @PostMapping("/rentals")
    public String createRental() {
        return "rental created";
    }
}

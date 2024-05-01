package com.chatop.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Token {

    @Schema(
            description = "Token returned when a user is successfully connected or created.",
            name = "token",
            type = "String",
            example = "eyJhbGciOJIUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoic...")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

package com.chatop.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;

public class UserDTO {

    @Schema(
            description = "email of the user",
            name = "email",
            type = "string",
            example = "test@test.com")
    private final String email;

    @Schema(
            description = "name of the user",
            name = "name",
            type = "string",
            example = "test TEST")
    private final String name;

    @Schema(
            description = "password of the user",
            name = "password",
            type = "string",
            example = "test!31")
    private final String password;


    public UserDTO(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
